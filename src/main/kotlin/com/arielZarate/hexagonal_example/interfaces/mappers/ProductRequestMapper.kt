package com.arielZarate.hexagonal_example.interfaces.mappers

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.interfaces.model.ProductRequest
import org.springframework.stereotype.Component


@Component
class ProductRequestMapper {


    fun toDomain(productRequest: ProductRequest): Product {

        return Product(
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