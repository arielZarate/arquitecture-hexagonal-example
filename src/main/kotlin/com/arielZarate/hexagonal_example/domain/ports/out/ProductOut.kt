package com.arielZarate.hexagonal_example.domain.ports.out

import com.arielZarate.hexagonal_example.domain.model.Product

interface ProductOut {

    fun findProductById(id: Int): Product?

    fun getAllProducts():List<Product>

    fun saveProduct(product: Product): Product
}