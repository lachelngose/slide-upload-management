package org.example.router

import org.example.handler.SlideManagementHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class SlideManagementRouter {
    private val path = "/api/v1/slides"

    @Bean
    fun slideManagementRequestRouter(
        slideManagementHandler: SlideManagementHandler,
    ) = router {
        path.nest {
            POST("", slideManagementHandler::uploadSlide)
        }

        "$path/{id}".nest {
            GET("", slideManagementHandler::getSlide)
        }

        "$path/list".nest {
            GET("", slideManagementHandler::getSlideSummaryList)
        }
    }
}