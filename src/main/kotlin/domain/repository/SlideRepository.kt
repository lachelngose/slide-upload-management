package org.example.domain.repository

import org.example.domain.model.Slide
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SlideRepository: JpaRepository<Slide, UUID>