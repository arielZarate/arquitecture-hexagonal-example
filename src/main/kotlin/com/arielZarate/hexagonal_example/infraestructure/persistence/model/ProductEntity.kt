package com.arielZarate.hexagonal_example.infraestructure.persistence.model

import jakarta.persistence.*


@Entity
@Table(name = "product")
data class ProductEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    @Embedded
    val rating:RatingEntity
){}


