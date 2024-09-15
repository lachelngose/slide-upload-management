package org.example.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class AnalysisRequestHandler {
    fun requestAnalysis(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getAnalysis(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getAnalysisRequestProgress(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getAnalysisSummaryList(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getAnalysisRequestHistories(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }
}