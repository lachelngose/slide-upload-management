package org.example.domain.repository

import org.example.domain.model.AnalysisRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnalysisRequestRepository: JpaRepository<AnalysisRequest, Long> {
}