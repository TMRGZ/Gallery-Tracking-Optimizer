package com.rviewer.skeletons.infrastructure.aspect;

import com.rviewer.skeletons.domain.annotation.ReactiveCacheable;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.expression.ExpressionException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Aspect
@Component
public class ReactiveCacheableAspect {

    @Autowired
    private ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate;

    private final SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    @Around("@annotation(reactiveCacheable)")
    public Object reactiveCacheable(ProceedingJoinPoint joinPoint, ReactiveCacheable reactiveCacheable) throws Throwable {
        Object result = joinPoint.proceed();
        String cacheName = reactiveCacheable.value();
        String cacheKey = generateCacheKey(reactiveCacheable.key());
        Class<?> returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();


        log.info("CACHE: {} {} {}", cacheName, cacheKey, returnType);

        if (returnType.equals(Mono.class)) {
            result = manageMonoCache((Mono<?>) result, cacheKey);

        } else if (returnType.equals(Flux.class)) {
            result = manageFluxCache((Flux<?>) result, cacheKey);
        }

        return result;
    }

    private Mono manageMonoCache(Mono<?> monoResult, String key) {
        return monoResult;
    }

    private Flux<?> manageFluxCache(Flux<?> fluxResult, String key) {
        return reactiveRedisTemplate.opsForList().range(key, 0, -1)
                .switchIfEmpty(fluxResult)
                .collectList()
                .flatMapMany(resultList -> reactiveRedisTemplate.opsForList().rightPushAll(key, resultList))
                .thenMany(fluxResult);
    }

    private String generateCacheKey(String keyToParse) {
        String key = keyToParse;

        try {
            key = Optional.ofNullable(spelExpressionParser.parseExpression(keyToParse).getValue())
                    .map(Object::toString)
                    .orElseThrow();
        } catch (ExpressionException ignored) {
        }

        return key;
    }
}
