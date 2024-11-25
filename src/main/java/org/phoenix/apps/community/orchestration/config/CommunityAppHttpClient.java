package org.phoenix.apps.community.orchestration.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CommunityAppHttpClient {

    @Bean
    @Qualifier("BlockingWebClient")
    public WebClient.Builder getBlockingWebClient() {
        return WebClient.builder();
    }
}
