package org.example.dto

import org.example.domain.model.AnalysisResult
import org.example.domain.model.AnalysisResultStatus
import java.time.LocalDateTime

data class AnalysisResultDto(
    val requestedBy: String,
    val requestedAt: LocalDateTime,
    val slideImageUrl: String,
    val analysisResultStatus: AnalysisResultStatus,
    val decision: Boolean,
    val score: Double,
    val intratumoralTILDensityDto: IntratumoralTILDensityDto,
    val stromalTILDensityDto: StromalTILDensityDto,
    val errorMessage: String? = null,
)

data class IntratumoralTILDensityDto(
    val min: Double,
    val max: Double,
    val avg: Double
) {
    companion object {
        fun fromAnalysisResult(analysisResult: AnalysisResult): IntratumoralTILDensityDto {
            return IntratumoralTILDensityDto(
                min = analysisResult.intratumoralDensityMin,
                max = analysisResult.intratumoralDensityMax,
                avg = analysisResult.intratumoralDensityAvg
            )
        }
    }
}

data class StromalTILDensityDto(
    val min: Double,
    val max: Double,
    val avg: Double
) {
    companion object {
        fun fromAnalysisResult(analysisResult: AnalysisResult): StromalTILDensityDto {
            return StromalTILDensityDto(
                min = analysisResult.stromalDensityMin,
                max = analysisResult.stromalDensityMax,
                avg = analysisResult.stromalDensityAvg
            )
        }
    }
}