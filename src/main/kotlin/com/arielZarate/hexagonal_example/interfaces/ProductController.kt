package com.arielZarate.hexagonal_example.interfaces


import com.arielZarate.hexagonal_example.domain.model.Product;
import com.arielZarate.hexagonal_example.domain.ports.`in`.GetProductService
import com.arielZarate.hexagonal_example.interfaces.error.exception.CustomException
import com.arielZarate.hexagonal_example.interfaces.mappers.ProductRequestMapper
import com.arielZarate.hexagonal_example.interfaces.mappers.ProductResponseMapper;
import com.arielZarate.hexagonal_example.interfaces.model.ProductRequest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/products")
class ProductController(
    private val getProductService: GetProductService,
    private val productResponseMapper: ProductResponseMapper,
    private val productRequestMapper: ProductRequestMapper
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<Any> {
            val products: List<Product> = getProductService.getAllProducts()
         return   ResponseEntity.ok(products.map { productResponseMapper.fromEntity(it) })

    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<Any> {
        val product: Product? = getProductService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product?.let { productResponseMapper.fromEntity(it) })
    }



    @PostMapping
    fun createProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<Any> {
        /// ejempko de un mapeo de datos con productRequest a Product //NO EXPONEMOS LA IDENTIDAD
        val product = productRequestMapper.toDomain(productRequest)
        val createdProduct: Product = getProductService.createProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseMapper.fromEntity(createdProduct))
    }
}