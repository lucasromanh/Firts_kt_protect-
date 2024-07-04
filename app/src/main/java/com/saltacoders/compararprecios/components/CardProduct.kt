package com.saltacoders.compararprecios.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saltacoders.compararprecios.model.Product

@Composable
fun CardProduct(product: Product) {
    var nombre = "Nombre del Producto"
    var precio = 0.0
    var cantidad = 0

    Card() {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp).height(52.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "Nombre: ${product.name}")
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(56.dp)

            ) {
                Text(text = "Precio: ${product.price}")

                Text(text = "Cantidad: ${product.quantity}")
            }
        }
    }
}
