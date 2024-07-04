package com.saltacoders.compararprecios.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.saltacoders.compararprecios.components.AddDialog
import com.saltacoders.compararprecios.components.AddFab
import com.saltacoders.compararprecios.components.CardProduct
import com.saltacoders.compararprecios.components.FinishButton
import com.saltacoders.compararprecios.components.PreciosAppBar
import com.saltacoders.compararprecios.model.Product
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun Principal() {
    var context = LocalContext.current

    var isShowingDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var productName by rememberSaveable {
        mutableStateOf("")
    }
    var productPrice by rememberSaveable {
        mutableStateOf("")
    }
    var productQuantity by rememberSaveable {
        mutableStateOf("")
    }

    var productsState = MutableStateFlow<ProductState>(ProductState())

    Scaffold(
        topBar = { PreciosAppBar() },
        floatingActionButton = { AddFab { isShowingDialog = true } }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(productsState.value.products) { index, product ->
                    CardProduct(product = product)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            FinishButton {
                Toast.makeText(
                    context,
                    "Para capo\nestas apurado?\nno lo hice todavia",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        if (isShowingDialog) {
            AddDialog(
                productName = productName,
                onProductNameChange = { productName = it },
                productPrice = productPrice,
                onPriceChange = {
                    try {
                        productPrice = it
                    } catch (e: NumberFormatException) {
                        Log.e("Parsing Error", "Error parsing price")
                    }
                },
                productQuantity = productQuantity,
                onQuantityChange = { productQuantity = it },
                onSave = {
                    productsState.value = productsState.value.copy(
                        products = productsState.value.products + Product(
                            name = productName,
                            price = productPrice,
                            quantity = productQuantity
                        )
                    )
                    productName = ""
                    productPrice = ""
                    productQuantity = ""
                },
                isShowingDialog = { isShowingDialog = false })
        }
    }
}

