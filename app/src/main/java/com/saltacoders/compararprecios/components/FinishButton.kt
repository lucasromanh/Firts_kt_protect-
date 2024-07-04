package com.saltacoders.compararprecios.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun FinishButton(findCheapest: () -> Unit) {
    Button(
        modifier = Modifier
            .width(350.dp)
            .padding(start = 8.dp, end = 48.dp, bottom = 16.dp),
        onClick = { findCheapest() }) {
        Text(text = "Buscar")
    }
}