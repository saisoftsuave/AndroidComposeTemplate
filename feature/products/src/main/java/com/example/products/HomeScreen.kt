package com.example.products

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridCells.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.TabletMac
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.ui.utils.UiState
import com.example.ui.utils.UiState.Idle


data class CategoryItem(
    val name: String,
    val icon: ImageVector,
    val color: Color
)


@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val productScreenState = viewModel.productsState.collectAsState()

    val items = listOf<CategoryItem>(
        CategoryItem(name = "Phones", icon = Icons.Default.PhoneAndroid, color = Color.LightGray),
        CategoryItem(name = "Tablets", icon = Icons.Default.TabletMac, color = Color.LightGray),
        CategoryItem(name = "Audio", icon = Icons.Default.Audiotrack, color = Color.LightGray),
        CategoryItem(name = "TVs", icon = Icons.Default.Tv, color = Color.LightGray)
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
        when (val state = productScreenState.value) {
            is UiState.Loading, Idle -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(paddingValue)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProductSearchBar()
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Categories",
                            style = MaterialTheme.typography.titleMedium
                        )
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
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Flash Sale", style = MaterialTheme.typography.titleMedium)
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
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Popular Products", style = MaterialTheme.typography.titleMedium)
                        LazyVerticalGrid(
                            columns = Fixed(2),
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
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Recently Viewed", style = MaterialTheme.typography.titleMedium)
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
                    }
                }
            }

            is UiState.Success -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(paddingValue)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProductSearchBar()
                    HomePagerComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Categories",
                            style = MaterialTheme.typography.titleMedium
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            items(items.size) { index ->
                                val category = items[index]
                                CategoryItemCircularView(
                                    categoryName = category.name,
                                    categoryImage = category.icon
                                ) {
                                    println("Clicked on ${category.name}")
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
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(state.data.size) { index ->
                                val product = state.data[index]
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
                            Text(
                                text = "See All",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.clickable { /* Navigate to full list */ }
                            )
                        }
                        LazyVerticalGrid(
                            columns = Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(540.dp),
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            userScrollEnabled = false
                        ) {
                            items(state.data) { product ->
                                ProductItemView(
                                    name = product.name,
                                    price = product.price.toFloat(),
                                    rating = 5,
                                    image = product.image,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    println("Clicked on ${product.name}")
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
                            Text(
                                text = "See All",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.clickable { /* Navigate to full recently viewed list */ }
                            )
                        }
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(state.data.size) { index ->
                                val product = state.data[index]
                                ProductItemView(
                                    name = product.name,
                                    price = product.price.toFloat(),
                                    rating = 3,
                                    image = product.image
                                ) {
                                    println("Clicked on ${product.name}")
                                }
                            }
                        }
                    }
                }
            }

            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Error",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = state.message,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Button(onClick = { viewModel.retry() }) {
                            Text("Retry")
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