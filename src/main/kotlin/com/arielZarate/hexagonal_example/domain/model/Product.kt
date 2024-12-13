package com.arielZarate.hexagonal_example.domain.model


data class Product(
    val id: Int?=null,//el id es opcional ya que lo genera la bd
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
) {
    data class Rating(val rate: Double, val count: Int)
}