package com.saltacoders.compararprecios.screens

import com.saltacoders.compararprecios.model.Product

data class ProductState(
    val isAddingProduct: Boolean = false,
    var products: List<Product> = emptyList()
)
