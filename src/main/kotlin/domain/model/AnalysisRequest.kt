package org.example.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Entity
@Table(
    indexes = [Index(name = "idx_slide_id", columnList = "slide_id"),
        Index(name = "idx_status_created_at", columnList = "status, created_at"),
        Index(name = "idx_created_at", columnList = "created_at")
    ]
)
class AnalysisRequest(
    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slide_id")
    val slide: Slide,

    @Column(columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    val status: RequestStatus,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    val result: AnalysisResult? = null,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),

    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
)

enum class RequestStatus {
    REQUESTED,
    IN_PROGRESS,
    COMPLETED,
    FAILED,
}