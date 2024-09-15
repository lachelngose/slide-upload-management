package org.example.handler

import org.example.service.AnalysisService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Component
class AnalysisRequestHandler(private val analysisService: AnalysisService) {
    fun requestAnalysis(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getAnalysis(request: ServerRequest): Mono<ServerResponse> {
        val slideId = runCatching {
            UUID.fromString(request.pathVariable("id"))
        }.getOrElse {
            return ServerResponse.badRequest().build()
        }

        val result = analysisService.getAnalysis(slideId)
        return ok().bodyValue(result).toMono()
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