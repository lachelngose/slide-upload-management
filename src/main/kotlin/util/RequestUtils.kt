package org.example.util

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.*

object RequestUtils {
    fun extractUUID(request: ServerRequest, paramName: String): Mono<UUID> {
        return runCatching {
            UUID.fromString(request.pathVariable(paramName))
        }.fold(
            onSuccess = { Mono.just(it) },
            onFailure = { ServerResponse.badRequest().build().then(Mono.empty()) }
        )
    }
}