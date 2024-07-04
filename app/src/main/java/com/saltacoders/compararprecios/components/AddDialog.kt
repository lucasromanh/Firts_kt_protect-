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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddDialog(isShowingDialog: () -> Unit) {
    val prod = "Nombre del Producto"

    AlertDialog(
        title = {
            Text(
                text = "Agregar Producto",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = { AddProductForm() },
        onDismissRequest = { isShowingDialog() },
        confirmButton = {
            Button(onClick = { /*TODO*/ }) {
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
private fun AddProductForm() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextFormField(
            label = "Nombre del Producto",
            onChange = {})

    }
}

@Composable
private fun TextFormField(numeric: Boolean = false, label: String, onChange: () -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(5.dp),
        maxLines = 1,
        minLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (numeric) KeyboardType.Number else KeyboardType.Text
        ),
        label = { Text(text = label)},
        value = "", onValueChange = {}
    )
}
