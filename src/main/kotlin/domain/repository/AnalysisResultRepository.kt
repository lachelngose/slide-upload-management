package org.example.domain.repository

import org.example.domain.model.AnalysisRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnalysisResultRepository: JpaRepository<AnalysisRequest, UUID> {
    fun findBySlideId(slideId: UUID): List<AnalysisRequest>
}