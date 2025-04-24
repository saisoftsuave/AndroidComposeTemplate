package com.example.products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.CategoryItemCircularView
import com.example.ui.HomePagerComponent
import com.example.ui.ProductItemView
import com.example.ui.ProductSearchBar
import com.example.ui.ShimmerBox
import com.example.ui.ShimmerCategoryItem
import com.example.ui.ShimmerProductItem

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var isLoading = viewModel.isLoading.collectAsState()
    val productScreenState = viewModel.productsState.collectAsState()

    val items = listOf(
        "Phones" to Color.Red,
        "Tablets" to Color.Green,
        "Audio" to Color.Blue,
        "TVs" to Color.Magenta,
    )
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Spa, contentDescription = "")
                    Text(
                        "TechGadgets",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Spa, contentDescription = "")
                    Icon(Icons.Filled.Spa, contentDescription = "")
                }
            }
        }
    ) { paddingValue ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValue)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductSearchBar()
            if (isLoading.value) {
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp)
                )
            } else {
                HomePagerComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                )
                if (isLoading.value) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(5) {
                            ShimmerCategoryItem()
                        }
                    }
                } else {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(items.size) { index ->
                            val (name, color) = items[index]
                            CategoryItemCircularView(
                                categoryName = name,
                            ) {
                                println("Clicked on $name")
                            }
                        }
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Flash Sale", style = MaterialTheme.typography.titleMedium)
                    if (!isLoading.value) {
                        Row(
                            modifier = Modifier
                                .background(
                                    Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Ends in: ", style = MaterialTheme.typography.bodyMedium)
                            Text("06:00:10", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
                if (isLoading.value) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(3) {
                            ShimmerProductItem()
                        }
                    }
                } else {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(productScreenState.value.size) { index ->
                            val product = productScreenState.value[index]
                            ProductItemView(
                                name = product.name,
                                price = product.price.toFloat(),
                                rating = 4,
                                image = product.image
                            ) {
                                println("Clicked on ${product.name}")
                            }
                        }
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Popular Products", style = MaterialTheme.typography.titleMedium)
                    if (!isLoading.value) {
                        Text(
                            text = "See All",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable { /* Navigate to full list */ }
                        )
                    }
                }
                if (isLoading.value) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(700.dp),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        userScrollEnabled = false
                    ) {
                        items(6) {
                            ShimmerProductItem()
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        userScrollEnabled = false
                    ) {
                        items(sampleProducts) { product ->
                            ProductItemView(
                                name = product.productName,
                                price = product.price,
                                rating = product.ratting,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                println("Clicked on ${product.productName}")
                            }
                        }
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Recently Viewed", style = MaterialTheme.typography.titleMedium)
                    if (!isLoading.value) {
                        Text(
                            text = "See All",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable { /* Navigate to full recently viewed list */ }
                        )
                    }
                }
                if (isLoading.value) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(3) {
                            ShimmerProductItem()
                        }
                    }
                } else {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(sampleProducts.size) { index ->
                            val product = sampleProducts[index]
                            ProductItemView(
                                name = product.productName,
                                price = product.price,
                                rating = product.ratting,
                            ) {
                                println("Clicked on ${product.productName}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenLoadingPreview() {
    MaterialTheme {
        ProductScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenLoadedPreview() {
    MaterialTheme {
        ProductScreen()
    }
}