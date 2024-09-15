package org.example.handler

import org.example.domain.service.SlideManagementService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class SlideManagementHandler(
    private val slideManagementService: SlideManagementService,
) {
    fun uploadSlide(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getSlide(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getSlideSummaryList(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }
}