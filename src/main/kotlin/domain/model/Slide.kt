package org.example.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Entity
@Table(
    indexes = [
        Index(name = "idx_created_at", columnList = "created_at"),
        Index(name = "idx_title, created_at_desc", columnList = "title, created_at desc"),
    ]
)
class Slide(
    @Id
    val id: UUID = UUID.randomUUID(),

    val title: String,

    val size: Long,

    val author: String,

    @NotNull
    val url: String,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),

    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
)