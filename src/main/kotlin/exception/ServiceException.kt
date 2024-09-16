package org.example.exception

import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

data class ServiceException(
    override var message: String,
    val code: String,
) : RuntimeException(message)

fun ErrorType.toServiceException(message: String? = null): Throwable {
    return ServiceException(code = this.name, message = message ?: this.message)
}

fun handleError(throwable: Throwable): Mono<ServerResponse> {
    return if (throwable is ServiceException) {
        ServerResponse.badRequest().bodyValue(throwable.message).toMono()
    } else {
        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValue(throwable.localizedMessage).toMono()
    }
}