package com.applications.DataAnalysis.config

import org.apache.pulsar.client.api.PulsarClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PulsarConfig {

    @Bean
    fun pulsarClient(): PulsarClient {
        return PulsarClient.builder()
            .serviceUrl("pulsar://localhost:6650")
            .build()
    }
}
