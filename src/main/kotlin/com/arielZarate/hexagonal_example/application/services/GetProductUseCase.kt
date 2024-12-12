package com.arielZarate.hexagonal_example.application.services

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.domain.ports.`in`.GetProductService
import com.arielZarate.hexagonal_example.domain.ports.out.ProductOut


//esto es un mock devuelve un objeto de prueba para probar el controller
//import com.arielZarate.hexagonal_example.utils.MockProducts

import org.springframework.stereotype.Service


@Service
class GetProductUseCase (
    private val productOut: ProductOut
): GetProductService {


    /*
    //private val products = MockProducts.getMockProducts()
    override fun getProductById(id: Int): Product? {
        return products.find{it.id==id}
    }

    override fun getAllProducts(): List<Product> {
        return products
    }
  * */
    override fun getProductById(id: Int): Product? {
      return productOut.findProductById(id)
    }

    override fun getAllProducts(): List<Product> {
        return productOut.getAllProducts()
    }

    override fun createProduct(product: Product): Product {
       return productOut.saveProduct(product);
    }
}