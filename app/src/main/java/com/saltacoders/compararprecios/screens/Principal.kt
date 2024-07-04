package com.saltacoders.compararprecios.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.saltacoders.compararprecios.components.AddDialog
import com.saltacoders.compararprecios.components.AddFab
import com.saltacoders.compararprecios.components.PreciosAppBar

@Composable
fun Principal() {
    var isShowingDialog by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = { PreciosAppBar() },
        floatingActionButton = { AddFab { isShowingDialog = true } }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(2) {
                TextField(value = "", onValueChange = {})
            }

        }
        if (isShowingDialog) {
            AddDialog{isShowingDialog = false}
        }
    }
}