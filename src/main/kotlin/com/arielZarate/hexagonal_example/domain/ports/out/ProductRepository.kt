package com.arielZarate.hexagonal_example.domain.ports.out

import com.arielZarate.hexagonal_example.domain.model.Product

interface ProductRepository {

    fun findProductById(id: Int): Product?

    fun getAllProducts():List<Product>
}