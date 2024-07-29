package com.danp.artexploreapp.paiting.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danp.artexploreapp.R
import com.danp.artexploreapp.paiting.domain.Painting
import com.danp.artexploreapp.ui.theme.PrimaryColor
import com.danp.artexploreapp.util.MyTopBar
import com.google.gson.Gson

@Composable
fun PaintingCard(navController: NavController, paintingJson: String?) {
    val gson = Gson()
    val painting = paintingJson?.let { gson.fromJson(it, Painting::class.java) }
    Scaffold(
        topBar = { MyTopBar(navController = navController, "Painting information", true) }
    ) { ip ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ip)
        ) {
            Card(
                modifier = Modifier
                    .width(350.dp)
                    .align(Alignment.Center)
                    .padding(top = 18.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Card(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(top = 10.dp),
                                shape = RoundedCornerShape(19.dp),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .background(PrimaryColor)
                                        .padding(8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_favorite),
                                        contentDescription = "favorites",
                                        colorFilter = ColorFilter.tint(Color.DarkGray)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_share),
                                        contentDescription = "favorites",
                                        colorFilter = ColorFilter.tint(Color.DarkGray)
                                    )
                                }
                            }
                        }
                    }
                    item {
                        AsyncImage(
                            model = painting!!.imageURL,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.FillBounds,
                        )
                        IconButtonWithText(
                            onClick = { /* Handle click */ },
                            modifier = Modifier.padding(8.dp)
                        )

                        Text(
                            text = painting.title,
                            fontSize = 35.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = painting.author,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            color = Color.Black,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = painting.description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            color = Color.Black,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IconButtonWithText(onClick: () -> Unit, modifier: Modifier) {
    Button(onClick = onClick) {
        Image(
            painter = painterResource(id = R.drawable.ic_favorite),
            contentDescription = "favorites",
            colorFilter = ColorFilter.tint(Color.DarkGray)
        )

        Text("English")
    }
}