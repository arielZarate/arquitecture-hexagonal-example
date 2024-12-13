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
        return try {
            val products: List<Product> = getProductService.getAllProducts()
            ResponseEntity.ok(products.map { productResponseMapper.fromEntity(it) })
        } catch (e: IllegalArgumentException) {
             ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud incorrecta: ${e.message}")
        }catch (e: NoSuchElementException) {
            // Si no se encuentran productos
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos: ${e.message}")
        } catch (e: CustomException) {
            // Si ocurre un error relacionado con la base de datos
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error al acceder a la base de datos: ${e.message}")
        } catch (e: Exception) {
            // Manejador genérico para cualquier otro error
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: ${e.message}")
        }
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<Any> {
        return try {
            val product: Product = getProductService.getProductById(id)
                ?: return ResponseEntity(HttpStatus.NO_CONTENT)
            ResponseEntity.ok(productResponseMapper.fromEntity(product))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Error el buscar un product por id")
        }
    }



    @PostMapping
    fun createProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<Any> {
        return try {

            /// ejempko de un mapeo de datos con productRequest a Product //NO EXPONEMOS LA IDENTIDAD
            val  product=productRequestMapper.toDomain(productRequest)
            val createdProduct: Product = getProductService.createProduct(product)
            ResponseEntity.status(HttpStatus.CREATED).body(productResponseMapper.fromEntity(createdProduct))
        } catch (e: IllegalArgumentException) {
            // Error específico, por ejemplo, validación de argumentos
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos inválidos: ${e.message}")
        } catch (e: DataIntegrityViolationException) {
            // Error de integridad de datos (por ejemplo, clave duplicada en BD)
            ResponseEntity.status(HttpStatus.CONFLICT).body("Error de integridad de datos: ${e.message}")
        } catch (e: Exception) {
            // Error genérico
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: ${e.message}")
        }
    }
}