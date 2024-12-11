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
│           └── ProductRepository.kt
├── application
│   └── services
│       └── GetProductUseCase.kt
├── infrastructure
│   ├── adapters
│   │   ├── ProductRepositoryAdapter.kt
│   │   └── ProductApiAdapter.kt
│   ├── rest
│   │   ├── models
│   │   │   └── ProductApiModel.kt
│   │   └── mappers
│   │       └── ProductApiMapper.kt
├── interfaces
│   └── rest
│       ├── ProductController.kt
│       └── models
│           └── ProductResponse.kt
└── utils
    └── MockProducts.kt

```