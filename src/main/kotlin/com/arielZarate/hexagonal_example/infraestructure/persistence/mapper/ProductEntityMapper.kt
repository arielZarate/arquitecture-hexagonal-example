package com.arielZarate.hexagonal_example.infraestructure.persistence.mapper

import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.infraestructure.persistence.model.ProductEntity

class ProductEntityMapper(
    private val ratingEntityMapper: RatingEntityMapper
) {


    fun toDomain(productEntity: ProductEntity):Product{
        return Product(
            id = productEntity.id,
            title = productEntity.title,
            price = productEntity.price,
            description = productEntity.description,
            category = productEntity.category,
            image = productEntity.image,

            //a la vez cada tengo un mapper en rating
            rating =ratingEntityMapper.toDomain(productEntity.rating)
            //Product.Rating(rate = productEntity.rating.rate, count = productEntity.rating.count)
        )
    }



    // Convierte de Product (modelo de dominio) a ProductEntity (entidad JPA)
    fun toEntity(product: Product): ProductEntity {
        return ProductEntity(
            id = product.id,
            title = product.title,
            price = product.price,
            description = product.description,
            category = product.category,
            image = product.image,
            //mape de ratingEntitymapper
           rating = ratingEntityMapper.toEntity(product.rating)
             //  product.rating.toEntity() /
        )
    }
}