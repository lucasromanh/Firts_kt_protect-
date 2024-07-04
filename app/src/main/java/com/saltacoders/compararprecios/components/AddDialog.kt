package com.saltacoders.compararprecios.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AddDialog(
    isShowingDialog: () -> Unit,
    onSave: () -> Unit,
    productQuantity: String,
    productPrice: String,
    productName: String,
    onProductNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit
) {
    val prod = "Nombre del Producto"

    AlertDialog(
        title = {
            Text(
                text = "Agregar Producto",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            AddProductForm(
                productName = productName,
                productPrice = productPrice,
                productQuantity = productQuantity,
                onProductNameChange = { onProductNameChange(it) },
                onPriceChange = { onPriceChange(it) },
                onQuantityChange = { onQuantityChange(it) }
            )
        },
        onDismissRequest = { isShowingDialog() },
        confirmButton = {
            Button(onClick = {
                onSave()
                isShowingDialog()
            }) {
                Text(text = "Confirmar")
            }
        },
        dismissButton = {
            Button(onClick = { isShowingDialog() }) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Composable
private fun AddProductForm(
    productQuantity: String,
    productPrice: String,
    productName: String,
    onProductNameChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextFormField(
            value = productName,
            label = "Nombre del Producto",
            onChange = {
                onProductNameChange(it)
            })

        TextFormField(
            numeric = true,
            value = productPrice.toString(),
            label = "Precio",
            onChange = {
                onPriceChange(it)
            })
        TextFormField(
            numeric = true,
            value = productQuantity.toString(),
            label = "Cantidad",
            onChange = {
                onQuantityChange(it)
            })
    }
}

@Composable
private fun TextFormField(
    numeric: Boolean = false,
    label: String,
    onChange: (String) -> Unit,
    value: String
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(5.dp),
        maxLines = 1,
        minLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (numeric) KeyboardType.Decimal else KeyboardType.Text
        ),
        label = { Text(text = label) },
        value = value, onValueChange = { onChange(it) }
    )
}
