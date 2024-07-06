package com.saltacoders.compararprecios.screens

sealed interface ProductEvent {
    object ShowDialog: ProductEvent
    object HideDialog: ProductEvent
}