package com.arielZarate.hexagonal_example.infraestructure.adapters


import com.arielZarate.hexagonal_example.domain.model.Product
import com.arielZarate.hexagonal_example.domain.ports.out.ProductOut
import com.arielZarate.hexagonal_example.infraestructure.persistence.mapper.ProductEntityMapper
import com.arielZarate.hexagonal_example.infraestructure.persistence.mapper.RatingEntityMapper
import com.arielZarate.hexagonal_example.infraestructure.persistence.model.ProductEntity
import com.arielZarate.hexagonal_example.infraestructure.persistence.repositories.ProductRepository
import org.springframework.stereotype.Component


@Component
class ProductOutAdapter(
    private val productRepository: ProductRepository,
    //inyectamos los services
    private val productEntityMapper: ProductEntityMapper,
    private val ratingEntityMapper: RatingEntityMapper
):ProductOut {


    //======debo mapear cada consulta======
    override fun findProductById(id: Int) :Product{
        val productEntity:ProductEntity =productRepository.findById(id).orElse(null);
        return productEntityMapper.toDomain(productEntity);
    }

    override fun getAllProducts(): List<Product> {

        val productEntities = productRepository.findAll()
        return productEntities.map { 
            productEntity ->  productEntityMapper.toDomain(productEntity)
        }
    }

    override fun saveProduct(product: Product): Product {

        //mapeo el producto que ingresa a entity
        val productE=productEntityMapper.toEntity(product);

        val productG=productRepository.save(productE)//producto guarado esta como entity

        return productEntityMapper.toDomain(productG)
    }


}