package org.example.domain.repository

import org.example.domain.model.AnalysisRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnalysisRequestRepository: JpaRepository<AnalysisRequest, Long> {
    fun findAllOrderByCreatedAt(pageable: Pageable): Page<AnalysisRequest>

    fun findAllBySlideIdOrderByCreatedAt(slideId: UUID, pageable: Pageable): Page<AnalysisRequest>
}