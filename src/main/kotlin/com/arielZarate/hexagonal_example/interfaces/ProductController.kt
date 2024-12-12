package com.arielZarate.hexagonal_example.interfaces


import com.arielZarate.hexagonal_example.domain.model.Product;
import com.arielZarate.hexagonal_example.domain.ports.`in`.GetProductService
import com.arielZarate.hexagonal_example.interfaces.mappers.ProductMapper;
import com.arielZarate.hexagonal_example.interfaces.model.ProductRequest
import com.arielZarate.hexagonal_example.interfaces.model.ProductResponse;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/products")
class ProductController(
    private val getProductService: GetProductService,
    private val productMapper: ProductMapper
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductResponse>> {
        return try {
            val products: List<Product> = getProductService.getAllProducts()
            ResponseEntity.ok(products.map { productMapper.fromEntity(it) })
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<Any> {
        return try {
            val product: Product = getProductService.getProductById(id)
                ?: return ResponseEntity(HttpStatus.NO_CONTENT)
            ResponseEntity.ok(productMapper.fromEntity(product))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Error el crear un product por id")
        }
    }



    @PostMapping
    fun createProduct(@RequestBody productRequest: Product): ResponseEntity<Any> {
        return try {

            /// ejempko de un mapeo de datos con productResponse a Product //NO EXPONEMOS LA IDENTIDAD
            // val  product=productMapper.toDomain(productRequest)
            val createdProduct: Product = getProductService.createProduct(productRequest)
            ResponseEntity.status(HttpStatus.CREATED).body(productMapper.fromEntity(createdProduct))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Error al crear el producto")
        }
    }
}