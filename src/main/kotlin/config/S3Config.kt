package org.example.config

import io.awspring.cloud.s3.S3Template
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {
    @Bean
    fun s3Template(): S3Template {
        return S3Template() // S3 설정
    }
}
