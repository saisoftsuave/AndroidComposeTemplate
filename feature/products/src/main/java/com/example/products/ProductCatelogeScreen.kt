package com.example.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Products(
    val id: String,
    val name: String,
    val icon: ImageVector
)

@Composable
fun ProductCatalogScreen() {
    val products = listOf(
        Products("1", "Music", Icons.Default.MusicNote),
        Products("2", "Books", Icons.Default.Book),
        Products("3", "Games", Icons.Default.SportsEsports),
        Products("4", "Movies", Icons.Default.MusicNote), // You can replace with real icon later
    )

    ProductCatalogContent(
        products = products,
        onClickProduct = { id -> println("Clicked $id") }
    )
}

@Composable
fun ProductCatalogContent(
    products: List<Products>,
    onClickProduct: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { Products ->
            ProductItem(
                id = Products.id,
                name = Products.name,
                imageUrl = Products.icon,
                onClickProduct = onClickProduct
            )
        }
    }
}

@Composable
fun ProductItem(
    id: String,
    name: String,
    imageUrl: ImageVector,
    onClickProduct: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClickProduct(id) },
        shape = ShapeDefaults.Medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = imageUrl,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCatalogPreview() {
    MaterialTheme {
        ProductCatalogScreen()
    }
}