package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.infrastructure.rest.dataset.DatasetControllerApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasetClientConfig {

    @Bean
    public DatasetControllerApi datasetControllerApi() {
        return new DatasetControllerApi();
    }
}
