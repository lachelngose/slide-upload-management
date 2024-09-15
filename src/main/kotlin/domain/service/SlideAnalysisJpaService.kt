package org.example.domain.service

import org.example.domain.model.AnalysisRequest
import org.example.domain.model.AnalysisResult
import org.example.domain.model.Slide
import org.example.domain.repository.AnalysisRequestRepository
import org.example.domain.repository.AnalysisResultRepository
import org.example.domain.repository.SlideRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SlideAnalysisJpaService(
    private val slideRepository: SlideRepository,
    private val analysisRequestRepository: AnalysisRequestRepository,
    private val analysisResultRepository: AnalysisResultRepository,
) {
    fun getSlideById(slideId: UUID): Slide? {
        return slideRepository.findById(slideId).orElse(null)
    }

    fun getSlideList(title: String, offset: Long, limit: Long): List<Slide> {
        return slideRepository.findAllByTitleLikeOrderByCreatedAtDesc(
            title,
            PageRequest.of(offset.toInt(), limit.toInt())
        ).toList()
    }

    fun getAnalysisRequestList(offset: Long, limit: Long): List<AnalysisRequest> {
        return analysisRequestRepository.findAllOrderByCreatedAt(
            PageRequest.of(offset.toInt(), limit.toInt())
        ).toList()
    }

    fun getAnalysisRequestListBySlideId(slideId: UUID, offset: Long, limit: Long): List<AnalysisRequest> {
        return analysisRequestRepository.findAllBySlideIdOrderByCreatedAt(
            slideId,
            PageRequest.of(offset.toInt(), limit.toInt())
        ).toList()
    }

    fun getLatestAnalysisResultBySlideId(slideId: UUID): AnalysisResult? {
        return analysisResultRepository.findFirstBySlideIdOrderByCreatedAtDesc(slideId)
    }
}