package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.infrastructure.rest.dataset.DatasetControllerApi;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "endpoints.dataset")
public class DatasetClientConfig {

    @NotBlank
    private String url;

    @Bean
    public DatasetControllerApi datasetControllerApi() {
        DatasetControllerApi datasetControllerApi = new DatasetControllerApi();
        datasetControllerApi.getApiClient().setBasePath(url);
        return datasetControllerApi;
    }
}
