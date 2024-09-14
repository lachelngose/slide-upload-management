package org.example.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.http.codec.HttpMessageWriter
import org.springframework.http.codec.LoggingCodecSupport
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.util.concurrent.TimeUnit

@Configuration
@EnableWebFlux
class WebFluxConfig : WebFluxConfigurer {

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { configurer: ClientCodecConfigurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 50) }
            .build()
        exchangeStrategies
            .messageWriters().stream()
            .filter { obj: HttpMessageWriter<*>? -> LoggingCodecSupport::class.java.isInstance(obj) }
            .forEach { writer: HttpMessageWriter<*> -> (writer as LoggingCodecSupport).isEnableLoggingRequestDetails = true }

        return WebClient.builder()
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient
                        .create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                        .doOnConnected { conn ->
                            conn.addHandlerLast(ReadTimeoutHandler(5, TimeUnit.SECONDS))
                                .addHandlerLast(WriteTimeoutHandler(70, TimeUnit.SECONDS))
                        }
                )
            )
            .exchangeStrategies(exchangeStrategies)
            .filter(
                ExchangeFilterFunction.ofRequestProcessor { clientRequest: ClientRequest ->
                    Mono.just(clientRequest)
                }
            )
            .filter(
                ExchangeFilterFunction.ofResponseProcessor { clientResponse: ClientResponse ->
                    Mono.just(clientResponse)
                }
            )
    }
}