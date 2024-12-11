package com.arielZarate.hexagonal_example.interfaces.rest;


import com.arielZarate.hexagonal_example.domain.ports.in.GetProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
class ProductController(
        private val getProductService:GetProductService,
        private val productMapper: ProductMapper
) {
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id:Integer): ResponseEntity<ProductResponse> {
        val product = getProductService.getProductById(id)
                ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(productMapper.mapToResponse(product))
    }
}