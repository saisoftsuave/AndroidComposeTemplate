package com.example.services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

data class ServiceCategory(
    val id: String,
    val name: String,
    val icon: ImageVector
)

@Composable
fun ServiceCatalogScreen() {
    val services = listOf(
        ServiceCategory("1", "Category 1", Icons.Default.Home),
        ServiceCategory("2", "Category 2", Icons.Default.Work),
        ServiceCategory("3", "Category 3", Icons.Default.Business),
        ServiceCategory("4", "Category 4", Icons.Default.Settings),
    )

    ServiceCatalogContent(
        services = services,
        onClickService = { id -> println("Clicked $id") }
    )
}

@Composable
fun ServiceCatalogContent(
    services: List<ServiceCategory>,
    onClickService: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(services) { service ->
            ServiceItem(
                id = service.id,
                name = service.name,
                icon = service.icon,
                onClickService = onClickService
            )
        }
    }
}

@Composable
fun ServiceItem(
    id: String,
    name: String,
    icon: ImageVector,
    onClickService: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClickService(id) },
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
                imageVector = icon,
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
fun ServiceCatalogPreview() {
    MaterialTheme {
        ServiceCatalogScreen()
    }
}