package com.arielZarate.hexagonal_example.application.services

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.domain.ports.`in`.GetProductService
import com.arielZarate.hexagonal_example.utils.MockProducts

import org.springframework.stereotype.Service


@Service
class GetProductUseCase : GetProductService {


    private val products = MockProducts.getMockProducts()
    override fun getProductById(id: Int): Product? {
        return products.find{it.id==id}
    }

    override fun getAllProducts(): List<Product> {
        return products
    }
}