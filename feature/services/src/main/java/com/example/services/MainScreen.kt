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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.BannerComponent
import com.example.ui.CategoryItemCircularView
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val mainScreenState = viewModel.mainState.collectAsState()

    val items = listOf<CategoryItem>(
        CategoryItem(name = "Category 1", icon = Icons.Default.Home, color = Color.LightGray),
        CategoryItem(name = "Category 2", icon = Icons.Default.Work, color = Color.LightGray),
        CategoryItem(name = "Category 3", icon = Icons.Default.Business, color = Color.LightGray),
        CategoryItem(name = "Category 4", icon = Icons.Default.Settings, color = Color.LightGray)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "On-Demand Services",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                actions = {
                    IconButton(onClick = { /* Profile action */ }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            // Main content
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(paddingValue)
            ) {
                // Search Bar
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Search services...",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                // Categories Section
                Text(
                    text = "Service Categories",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items.size) { index ->
                        val category = items[index]
                        CategoryCard(
                            name = category.name,
                            icon = category.icon
                        ) {
                            println("Clicked on ${category.name}")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Featured Services
                Text(
                    text = "Featured Services",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                when (val state = mainScreenState.value) {
                    is UiState.Loading -> {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(3) {
                                ShimmerServiceItem()
                            }
                        }
                    }

                    is UiState.Success -> {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(state.data.size) { index ->
                                val service = state.data[index]
                                ServiceItemView(
                                    name = service.name,
                                    price = service.price,
                                    rating = service.rating,
                                    image = service.image
                                ) {
                                    println("Clicked on ${service.name}")
                                }
                            }
                        }
                    }

                    is UiState.Error -> {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error loading services: ${state.message}",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    else -> {
                        // Idle state - show shimmer
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(3) {
                                ShimmerServiceItem()
                            }
                        }
                    }
                }

                }

            // Bottom navigation - custom implementation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                NavigationItem(
                    icon = Icons.Default.Home,
                    label = "Home",
                    isSelected = true
                ) { /* Handle home navigation */ }
                
                NavigationItem(
                    icon = Icons.Default.List,
                    label = "Services",
                    isSelected = false
                ) { /* Handle services navigation */ }
                
                NavigationItem(
                    icon = Icons.Default.ShoppingCart,
                    label = "Cart",
                    isSelected = false
                ) { /* Handle cart navigation */ }
                
                NavigationItem(
                    icon = Icons.Default.Person,
                    label = "Profile",
                    isSelected = false
                ) { /* Handle profile navigation */ }
            }
        }
    }
}

@Composable
fun NavigationItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

    @Composable
    fun CategoryCard(
        name: String,
        icon: ImageVector,
        onClick: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .size(100.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = name,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
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