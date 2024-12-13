# api fake store api arquitectura hexagonal




## arquitecturas
```kotlin

src
├── domain
│   ├── model
│   │   └── Product.kt
│   └── ports
│       ├── in
│       │   └── GetProductService.kt
│       └── out
│           └── ProductOut.kt
├── application
│   └── services
│       └── GetProductUseCase.kt
├── infrastructure
│   ├── adapters
│   │   ├── ProductOutAdapter.kt           # Adaptador para la salida de datos del producto
│   │   └── ProductApiAdapter.kt           # Adaptador para interactuar con APIs externas
│   ├── rest
│   │   ├── andriani
│   │   │   └── model                     # Modelos relacionados con Andriani (puede ser una API externa)
│   │   └── apiFakeStore
│   │       └── model                     # Modelos relacionados con FakeStore (otra API o fuente de datos)
│   ├── persistence
│   │   ├── mapper
│   │   │   ├── ProductEntityMapper.kt    # Mapeo entre la entidad Product y su representación en la base de datos
│   │   │   └── RatingEntityMapper.kt     # Mapeo entre la entidad Rating y su representación en la base de datos
│   │   ├── model
│   │   │   ├── ProductEntity.kt          # Entidad para la persistencia de datos de productos
│   │   │   └── RatingEntity.kt           # Entidad para la persistencia de datos de valoraciones
│   │   └── repositories
│   │       └── ProductRepository.kt      # Repositorio para interactuar con la base de datos
├── interfaces
│   ├── rest
│   │   ├── ProductController.kt           # Controlador REST para manejar las solicitudes de productos
│   │   ├── models
│   │   │   ├── ProductRequest.kt          # Modelo de solicitud para entrada de datos
│   │   │   ├── ProductResponse.kt         # Modelo de respuesta para salida de datos
│   │   └── mappers
│   │       ├── ProductRequestMapper.kt    # Mapper para convertir ProductRequest en modelo de dominio
│   │       └── ProductResponseMapper.kt   # Mapper para convertir Product en ProductResponse
│   ├── error
│   │   ├── exception
│   │   │   └── CustomException.kt         # Excepción personalizada
│   │   └── GlobalExceptionHandler.kt      # Manejador global de excepciones
└── utils
    └── MockProducts.kt

```


## Public Api

- URL FOR PUBLISHED DOCUMENTACTION

### https://documenter.getpostman.com/view/12679400/2sAYHxo4kp

###     Colection name
Fake store api

#### /api/products
![all](/images/all.png)


#### /api/productos/2
![all](/images/getId.png)

#### /api/products
![all](/images/create.png)