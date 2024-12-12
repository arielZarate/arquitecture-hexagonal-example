package com.arielZarate.hexagonal_example.interfaces.mappers

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.interfaces.model.ProductResponse
import org.springframework.stereotype.Component


@Component
class ProductMapper {

    fun fromEntitytoResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.image,
            rating = ProductResponse.RatingResponse(
                rate = product.rating.rate,
                count = product.rating.count
            )
        )
    }
}