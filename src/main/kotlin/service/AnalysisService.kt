package org.example.service

import org.example.domain.model.AnalysisRequest
import org.example.domain.model.AnalysisResultStatus
import org.example.domain.model.RequestStatus
import org.example.domain.service.SlideAnalysisJpaService
import org.example.dto.AnalysisResultDto
import org.example.dto.IntratumoralTILDensityDto
import org.example.dto.StromalTILDensityDto
import org.example.exception.ErrorType.*
import org.example.exception.toServiceException
import org.springframework.stereotype.Service
import java.util.*

@Service
class AnalysisService(private val analysisJpaService: SlideAnalysisJpaService) {
    fun getAnalysis(slideId: UUID): AnalysisResultDto {
        val slide = analysisJpaService.getSlideById(slideId)
            ?: throw SLIDE_NOT_FOUND.toServiceException()

        val result = analysisJpaService.getLatestAnalysisResultBySlideId(slide.id)
            ?: throw ANALYSIS_RESULT_NOT_FOUND.toServiceException()

        if (result.status != AnalysisResultStatus.FAILED && result.decision == null) {
            throw INVALID_ANALYSIS_RESULT.toServiceException()
        }

        return AnalysisResultDto(
            requestedBy = result.requestedBy,
            requestedAt = result.requestedAt,
            slideImageUrl = slide.url,
            analysisResultStatus = result.status,
            decision = result.decision ?: false,
            score = result.score,
            intratumoralTILDensityDto = IntratumoralTILDensityDto.fromAnalysisResult(result),
            stromalTILDensityDto = StromalTILDensityDto.fromAnalysisResult(result),
            errorMessage = result.errorMsg,
        )
    }

    fun getAnalysisRequestHistories(slideId: UUID, offset: Long, limit: Long): List<AnalysisRequest> {
        val slide = analysisJpaService.getSlideById(slideId)
            ?: throw SLIDE_NOT_FOUND.toServiceException()

        return analysisJpaService.getAnalysisRequestListBySlideId(slide.id, offset, limit)
    }

    fun getLatestAnalysisRequestProgress(slideId: UUID): RequestStatus {
        val slide = analysisJpaService.getSlideById(slideId)
            ?: throw SLIDE_NOT_FOUND.toServiceException()

        return analysisJpaService.getLatestAnalysisRequestBySlideId(slide.id)?.status
            ?: throw REQUEST_NOT_YET.toServiceException()
    }
}