package org.example.domain.model

import jakarta.persistence.*
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Entity
@Table(
    indexes = [
        Index(name = "idx_slide_id_created_at_desc", columnList = "slide_id, created_at desc"),
        Index(name = "idx_created_at", columnList = "created_at"),
    ]
)
class AnalysisResult(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slide_id")
    val slide: Slide,

    val requestedBy: String,

    val requestedAt: LocalDateTime,

    @Column(columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    val status: AnalysisResultStatus,

    val decision: Boolean? = null,

    @NotNull
    @field:DecimalMin("0.0")
    @field:DecimalMax("1.0")
    val score: Double = 0.0,

    val intratumoralDensityMin: Double = 0.0,

    val intratumoralDensityMax: Double = 0.0,

    val intratumoralDensityAvg: Double = 0.0,

    val stromalDensityMin: Double = 0.0,

    val stromalDensityMax: Double = 0.0,

    val stromalDensityAvg: Double = 0.0,

    @Column(columnDefinition = "TEXT")
    val errorMsg: String? = null,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),

    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
)

enum class AnalysisResultStatus {
    SUCCEEDED,
    FAILED,
}