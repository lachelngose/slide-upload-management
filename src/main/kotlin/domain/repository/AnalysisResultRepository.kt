package org.example.domain.repository

import org.example.domain.model.AnalysisRequest
import org.example.domain.model.AnalysisResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AnalysisResultRepository: JpaRepository<AnalysisResult, UUID> {
    fun findFirstBySlideIdOrderByCreatedAtDesc(slideId: UUID): AnalysisResult?
}