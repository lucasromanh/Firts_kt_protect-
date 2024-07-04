package com.saltacoders.compararprecios.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FinishButton() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(start = 48.dp, end = 48.dp, bottom = 16.dp),
        onClick = { }) {
        Text(text = "Buscar")
    }
}