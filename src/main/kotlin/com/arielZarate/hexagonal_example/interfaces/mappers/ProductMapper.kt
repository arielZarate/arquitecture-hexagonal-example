package com.arielZarate.hexagonal_example.interfaces.mappers

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.interfaces.model.ProductResponse
import org.springframework.stereotype.Component


@Component
class ProductMapper {

    fun fromEntity(product: Product): ProductResponse {
        return ProductResponse(
            id=product.id,
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.image,
            rating = ProductResponse.RatingResponse(
                rate = product.rating.rate,
                count = product.rating.count
            ),

        )
    }




    // Método para mapear de ProductRequest (datos del cliente) a Product (modelo de dominio)
    fun toDomain(productRequest: ProductResponse): Product {
        return Product(
            //id = 0, // El id es 0 cuando se crea un nuevo producto, será asignado por la base de datos
            id=productRequest.id,
            title = productRequest.title,
            price = productRequest.price,
            description = productRequest.description,
            category = productRequest.category,
            image = productRequest.image,
            rating = Product.Rating(
                rate = productRequest.rating.rate,
                count = productRequest.rating.count
            )
        )
    }
}