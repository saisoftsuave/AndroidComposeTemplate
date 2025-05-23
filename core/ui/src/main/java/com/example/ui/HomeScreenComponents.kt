package com.example.ui

import android.icu.number.Scale
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.android.material.bottomnavigation.BottomNavigationItemView


//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePagerComponent(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = Color.Black, shape = RoundedCornerShape(8.dp))
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Summer tech sale \n Up to 40% off on selected Item",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(vertical = 32.dp),
            textAlign = TextAlign.Center
        )
        //content()
    }
}


//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryItemCircularView(
    modifier: Modifier = Modifier,
    categoryName: String,
    @DrawableRes categoryImage: Int = R.drawable.apple_social_icon,
    onClickCategory: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color = Color.LightGray, shape = CircleShape)
                .clickable {
                    onClickCategory()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(categoryImage),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            categoryName,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductItemView(
    modifier: Modifier = Modifier,
    name: String,
    price: Float,
    rating: Int,
    image: String? = null,
    onProductClick: () -> Unit
) {
    Card(
        modifier = Modifier.width(170.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        if (image == null) {
            Image(
                painterResource(R.drawable.ic_onboarding_background),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.FillBounds,
            )
        } else {
            AsyncImage(
                model = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.FillBounds,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row {
                for (i in 1..rating) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color.Black
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$$price",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(color = Color.Black, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavigationItemView(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Icon(Icons.Filled.Home, contentDescription = "")
        Text("Home", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
    }
}