package com.arielZarate.hexagonal_example.infraestructure.persistence.model

import jakarta.persistence.Embeddable

@Embeddable
data class RatingEntity(
    val rate: Double,
    val count: Int
)