package org.example.handler

import org.example.exception.handleError
import org.example.service.AnalysisService
import org.example.util.RequestUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class AnalysisRequestHandler(private val analysisService: AnalysisService) {
    fun requestAnalysis(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getAnalysis(request: ServerRequest): Mono<ServerResponse> {
        val slideIdMono = RequestUtils.extractUUID(request, "id")

        return slideIdMono.flatMap { slideId ->
            val result = analysisService.getAnalysis(slideId)
            ok().bodyValue(result).toMono()
        }.onErrorResume {
            handleError(it)
        }
    }

    fun getAnalysisRequestProgress(request: ServerRequest): Mono<ServerResponse> {
        val slideIdMono = RequestUtils.extractUUID(request, "id")

        return slideIdMono.flatMap { slideId ->
            val result = analysisService.getLatestAnalysisRequestProgress(slideId)
            ok().bodyValue(result).toMono()
        }.onErrorResume {
            handleError(it)
        }
    }

    fun getAnalysisSummaryList(request: ServerRequest): Mono<ServerResponse> {
        // Your implementation here
        return ServerResponse.ok().build()
    }

    fun getAnalysisRequestHistories(request: ServerRequest): Mono<ServerResponse> {
        val slideIdMono = RequestUtils.extractUUID(request, "id")

        val offset = request.queryParam("offset").orElse("0").toLong()
        val limit = request.queryParam("limit").orElse("10").toLong()

        return slideIdMono.flatMap { slideId ->
            val result = analysisService.getAnalysisRequestHistories(slideId, offset, limit)
            ok().bodyValue(result).toMono()
        }.onErrorResume {
            handleError(it)
        }
    }
}