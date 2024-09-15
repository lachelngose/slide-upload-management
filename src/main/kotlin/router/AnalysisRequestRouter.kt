package org.example.router

import org.example.handler.AnalysisRequestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class AnalysisRequestRouter {
    private val path = "/api/v1/analysis"

    @Bean
    fun analysisRequestRouter(
        analysisRequestHandler: AnalysisRequestHandler,
    ) = router {
        path.nest {
            POST("", analysisRequestHandler::requestAnalysis)
        }

        "$path/{id}".nest {
            GET("", analysisRequestHandler::getAnalysis)
        }

        "$path/{id}/status".nest {
            GET("", analysisRequestHandler::getAnalysisRequestProgress)
        }

        "$path/list".nest {
            GET("", analysisRequestHandler::getAnalysisSummaryList)
        }

        "$path/{id}/histories".nest {
            GET("", analysisRequestHandler::getAnalysisRequestHistories)
        }
    }
}