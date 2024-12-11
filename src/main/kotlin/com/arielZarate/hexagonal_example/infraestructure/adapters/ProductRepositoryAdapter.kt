package com.arielZarate.hexagonal_example.infraestructure.adapters

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.domain.ports.out.ProductRepository
import com.arielZarate.hexagonal_example.utils.MockProducts
import org.springframework.stereotype.Component


@Component
class ProductRepositoryAdapter:ProductRepository {
    override fun findProductById(id: Int): Product? {
        return MockProducts.products.find { it.id == id }
    }

    override fun getAllProducts() {

    }
}