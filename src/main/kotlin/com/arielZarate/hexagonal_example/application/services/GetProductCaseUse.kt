package com.arielZarate.hexagonal_example.application.services

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.domain.ports.`in`.GetProductService
import com.arielZarate.hexagonal_example.domain.ports.out.ProductRepository
import org.springframework.stereotype.Service


@Service
class GetProductCaseUse(private val productRepository: ProductRepository) : GetProductService {
    override fun getProductById(id: Int): Product? {
        return productRepository.findProductById(id)
    }
}