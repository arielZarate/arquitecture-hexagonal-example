package com.arielZarate.hexagonal_example.utils

import com.arielZarate.hexagonal_example.domain.model.Product

object MockProducts {

    val products = listOf(
        Product(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rating = Product.Rating(rate = 3.9, count = 120)
        )
    )
}