package com.saltacoders.compararprecios

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Material3Typography
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class Product(val name: String, val quantity: Int, val price: Float)

@Composable
fun ProductComparisonApp() {
    var products by remember { mutableStateOf(listOf<Product>()) }
    var productName by remember { mutableStateOf(TextFieldValue()) }
    var productQuantity by remember { mutableStateOf(TextFieldValue()) }
    var productPrice by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Comparador de Precios", style = Material3Typography.h1)

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            BasicTextField(
                value = productName,
                onValueChange = { productName = it },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                singleLine = true,
                textStyle = Material3Typography.body1.copy(Material3Colors.onSurface)
            )
            BasicTextField(
                value = productQuantity,
                onValueChange = { productQuantity = it },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                singleLine = true,
                textStyle = Material3Typography.body1.copy(Material3Colors.onSurface)
            )
            BasicTextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = Material3Typography.body1.copy(Material3Colors.onSurface)
            )
            Button(onClick = {
                val name = productName.text
                val quantity = productQuantity.text.toIntOrNull() ?: 0
                val price = productPrice.text.toFloatOrNull() ?: 0f
                if (name.isNotEmpty() && quantity > 0 && price > 0f) {
                    products = products + Product(name, quantity, price)
                    productName = TextFieldValue()
                    productQuantity = TextFieldValue()
                    productPrice = TextFieldValue()
                }
            }) {
                Text("Agregar Producto")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (products.isNotEmpty()) {
                val bestProduct = products.minByOrNull { it.price / it.quantity }
                if (bestProduct != null) {
                    val bestPricePerUnit = bestProduct.price / bestProduct.quantity
                    var detailedComparison = "El producto más económico es ${bestProduct.name} con un precio por unidad de ${bestPricePerUnit.toFixed(2)}.\n\n"
                    if (products.size > 1 || bestProduct.quantity > 1) {
                        products.forEach { product ->
                            if (product != bestProduct) {
                                val pricePerUnit = product.price / product.quantity
                                if (pricePerUnit > bestPricePerUnit) {
                                    detailedComparison += "- ${product.name} tiene un precio por unidad de ${pricePerUnit.toFixed(2)}, lo que es más caro que ${bestProduct.name}."
                                    val extraQuantity = ((product.price - bestProduct.price) / bestPricePerUnit).toInt()
                                    if (extraQuantity > 0) {
                                        val totalLiters = bestProduct.quantity + extraQuantity
                                        detailedComparison += " Podrías llevar $extraQuantity unidades más de ${bestProduct.name} y tener $totalLiters lts a un precio por unidad de ${bestPricePerUnit.toFixed(2)}, lo que sería más barato que ${product.name}."
                                    } else {
                                        detailedComparison += " Sin embargo, no ganarías más litros al comprar más unidades de este producto."
                                    }
                                    detailedComparison += "\n"
                                }
                            }
                        }
                    }
                    showComparisonDialog(detailedComparison)
                }
            }
        }) {
            Text("Comparar Precios")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            products = emptyList()
        }) {
            Text("Reiniciar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        products.forEachIndexed { index, product ->
            ProductCard(product = product, onDelete = { products = products.toMutableList().also { it.removeAt(index) } })
        }
    }
}

@Composable
fun ProductCard(product: Product, onDelete: () -> Unit) {
    Card(modifier = Modifier.padding(bottom = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nombre del Producto: ${product.name}")
            Text("Cantidad: ${product.quantity}")
            Text("Precio: ${product.price}")
            Button(onClick = onDelete) {
                Text("Eliminar")
            }
        }
    }
}

@Composable
fun showComparisonDialog(text: String) {
    // Aquí puedes mostrar un diálogo o una alerta con el texto proporcionado
    // Puedes usar AlertDialog, Toast, o cualquier otro componente adecuado
    // Por ejemplo:
    AlertDialog(
        onDismissRequest = { /* No hagas nada */ },
        title = { Text(text = "Comparación de Precios") },
        text = { Text(text = text) },
        confirmButton = {
            Button(
                onClick = { /* Cerrar el diálogo */ }
            ) {
                Text("Cerrar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProductComparisonAppPreview() {
    ProductComparisonApp()
}

