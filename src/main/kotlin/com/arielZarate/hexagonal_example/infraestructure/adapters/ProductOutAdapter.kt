package com.arielZarate.hexagonal_example.infraestructure.adapters


import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.domain.ports.out.ProductOut
import com.arielZarate.hexagonal_example.infraestructure.persistence.repositories.ProductRepository
import org.springframework.stereotype.Component


@Component
class ProductOutAdapter(
    private val productRepository: ProductRepository
):ProductOut {
    override fun findProductById(id: Int): Product? {
      return  productRepository.findById(id).orElse(null);
    }

    override fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    override fun saveProduct(product: Product): Product {
      return  productRepository.save(product)
    }


}