package com.example.yumly
import androidx.compose.ui.tooling.preview.Preview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // 1. Search Bar
        SearchBar()
        Spacer(modifier = Modifier.height(12.dp))

        // 2. Map Alanı
        MapArea()
        Spacer(modifier = Modifier.height(12.dp))

        // 3. Kategori Butonları
        CategoryRow()
        Spacer(modifier = Modifier.height(12.dp))

        // 4. Kafe Listesi
        CafeList()
        Spacer(modifier = Modifier.weight(1f))

        // 5. Bottom Navigation
        BottomNavBar()
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Search...") },
        trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
fun MapArea() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        // Dummy turuncu markerlar
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color(0xFFFF5722), CircleShape)
                .align(Alignment.TopStart)
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color(0xFFFF5722), CircleShape)
                .align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color(0xFFFF5722), CircleShape)
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun CategoryRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDF4F3A))
        ) {
            Text("☕ Coffee", color = Color.White)
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDF4F3A))
        ) {
            Text("🍰 Dessert", color = Color.White)
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDF4F3A))
        ) {
            Text("🍔 Fast Food", color = Color.White)
        }
    }
}

@Composable
fun CafeList() {
    Column {
        CafeCard(cafeName = "Cafe Mocha", rating = 4.5, keywords = "Brownie, Latte")
        CafeCard(cafeName = "Sweet Point", rating = 4.2, keywords = "Cheesecake, Espresso")
    }
}


@Composable
fun CafeCard(
    cafeName: String,
    rating: Double,
    keywords: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp), // köşeleri oval
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFE5D6) // pastel turuncu (açık ton)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Fotoğraf kutusu
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Yazılar
            Column {
                Text(
                    text = cafeName,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(text = "⭐ $rating", style = MaterialTheme.typography.bodyMedium)
                Text(text = keywords, style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
            }
        }
    }
}


@Composable
fun BottomNavBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Text("🏠") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Text("⭐") },
            label = { Text("Favorites") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Text("🔍") },
            label = { Text("Search") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Text("👤") },
            label = { Text("Profile") }
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}

