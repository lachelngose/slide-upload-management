package org.example.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Entity
class AnalysisResult(
    @Id
    val id: UUID = UUID.randomUUID(),

    @OneToOne
    @JoinColumn(name = "slide_id")
    val slide: Slide,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),
)