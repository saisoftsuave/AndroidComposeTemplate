package com.example.services

import androidx.annotation.DrawableRes
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
import androidx.compose.material.icons.filled.*
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
import com.example.ui.ServiceItemView
import com.example.ui.GenericSearchBar
import com.example.ui.ShimmerBox
import com.example.ui.ShimmerCategoryItem
import com.example.ui.ShimmerServiceItem
import com.example.ui.utils.UiState
import com.example.ui.utils.UiState.Idle

data class CategoryItem(
    val name: String,
    val icon: ImageVector,
    val color: Color
)


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val mainScreenState = viewModel.mainState.collectAsState()

    val items = listOf<CategoryItem>(
        CategoryItem(name = "Service 1", icon = Icons.Default.Spa, color = Color.LightGray),
        CategoryItem(name = "Service 2", icon = Icons.Default.Spa, color = Color.LightGray),
        CategoryItem(name = "Service 3", icon = Icons.Default.Spa, color = Color.LightGray),
        CategoryItem(name = "Service 4", icon = Icons.Default.Spa, color = Color.LightGray)
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
                        "On-Demand Services",
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
        when (val state = mainScreenState.value) {
            is UiState.Loading, Idle -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(paddingValue)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    GenericSearchBar()
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
                        Text("Featured Services", style = MaterialTheme.typography.titleMedium)
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(3) {
                                ShimmerServiceItem()
                            }
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Popular Services", style = MaterialTheme.typography.titleMedium)
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
                                ShimmerServiceItem()
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
                                ShimmerServiceItem()
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
                    GenericSearchBar()
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
                            Text("Featured Services", style = MaterialTheme.typography.titleMedium)
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
                                val service = state.data[index]
                                ServiceItemView(
                                    name = service.name,
                                    price = service.price.toFloat(),
                                    rating = 4,
                                    image = service.image
                                ) {
                                    println("Clicked on ${service.name}")
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
                            Text("Popular Services", style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = "See All",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.clickable { /* Navigate to full list */ }
                            )
                        }
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(540.dp),
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            userScrollEnabled = false
                        ) {
                            items(state.data) { service ->
                                ServiceItemView(
                                    name = service.name,
                                    price = service.price.toFloat(),
                                    rating = 5,
                                    image = service.image,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    println("Clicked on ${service.name}")
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
                                val service = state.data[index]
                                ServiceItemView(
                                    name = service.name,
                                    price = service.price.toFloat(),
                                    rating = 3,
                                    image = service.image
                                ) {
                                    println("Clicked on ${service.name}")
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
fun MainScreenLoadingPreview() {
    MaterialTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenLoadedPreview() {
    MaterialTheme {
        MainScreen()
    }
}