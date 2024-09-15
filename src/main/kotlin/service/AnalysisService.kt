package org.example.service

import org.example.domain.model.AnalysisResultStatus
import org.example.domain.service.SlideAnalysisJpaService
import org.example.dto.AnalysisResultDto
import org.example.dto.IntratumoralTILDensityDto
import org.example.dto.StromalTILDensityDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class AnalysisService(private val analysisJpaService: SlideAnalysisJpaService) {
    fun getAnalysis(slideId: UUID): AnalysisResultDto {
        val slide = analysisJpaService.getSlideById(slideId)
            ?: throw IllegalArgumentException("Slide not found")

        return analysisJpaService.getLatestAnalysisResultBySlideId(slide.id)
            ?.let {
                if (it.status != AnalysisResultStatus.FAILED && it.decision == null) {
                    throw Exception("Analysis result is not illegal")
                }

                AnalysisResultDto(
                    requestedBy = it.requestedBy,
                    requestedAt = it.requestedAt,
                    slideImageUrl = slide.url,
                    analysisResultStatus = it.status,
                    decision = it.decision ?: false,
                    score = it.score,
                    intratumoralTILDensityDto = IntratumoralTILDensityDto.fromAnalysisResult(it),
                    stromalTILDensityDto = StromalTILDensityDto.fromAnalysisResult(it),
                    errorMessage = it.errorMsg,
                )
            } ?: throw IllegalArgumentException("Analysis result not found")
    }
}