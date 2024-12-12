package com.arielZarate.hexagonal_example.interfaces


import com.arielZarate.hexagonal_example.domain.model.Product;
import com.arielZarate.hexagonal_example.domain.ports.`in`.GetProductService
import com.arielZarate.hexagonal_example.interfaces.mappers.ProductMapper;
import com.arielZarate.hexagonal_example.interfaces.model.ProductResponse;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/products")
class ProductController(
    private val getProductService: GetProductService,
    private val productMapper: ProductMapper
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductResponse>> {
        return try {
            val products: List<Product> = getProductService.getAllProducts()
            ResponseEntity.ok(products.map { productMapper.toResponse(it) })
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<ProductResponse> {
        return try {
            val product: Product = getProductService.getProductById(id)
                ?: return ResponseEntity(HttpStatus.NO_CONTENT)
            ResponseEntity.ok(productMapper.toResponse(product))
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}