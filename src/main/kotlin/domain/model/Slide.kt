package org.example.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Entity
class Slide(
    @Id
    val id: UUID = UUID.randomUUID(),

    val title: String,

    val size: Long,

    val author: String,

    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),
)