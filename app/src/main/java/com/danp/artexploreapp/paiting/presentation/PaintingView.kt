package com.danp.artexploreapp.paiting.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.gson.Gson

@Composable
fun PaintingView(navController: NavController, paintingJson: String?) {

    val gson = Gson()
    val painting = paintingJson?.let { gson.fromJson(it, Painting::class.java) }
    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .width(350.dp)
                .align(Alignment.Center)
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
                                .padding(top = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .background(PrimaryColor)
                                    .padding(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_qr),
                                    contentDescription = "favorites"
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_qr),
                                    contentDescription = "favorites"
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
                            .aspectRatio(1f),
                        contentScale = ContentScale.FillBounds,
                    )

                    Text(
                        text = painting.title,
                        fontSize = 32.sp,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = painting.description,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

//
//@Composable
//fun Content(modifier: Modifier = Modifier) {
//    Box(modifier = modifier.fillMaxWidth()) {
//        Card(
//            modifier = Modifier
//                .width(350.dp)
//                .align(Alignment.Center)
//        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.LightGray)
//                    .padding(horizontal = 16.dp)
//            ) {
//                item {
//                    Box(
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Card(modifier = Modifier.align(Alignment.CenterEnd).padding( top=10.dp)) {
//                            Row(
//                                modifier = Modifier
//                                    .background(colorResource(id = R.color.backgroundApp))
//                                    .padding(8.dp)
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.ic_favorite),
//                                    contentDescription = "favorites"
//                                )
//                                Image(
//                                    painter = painterResource(id = R.drawable.ic_share),
//                                    contentDescription = "favorites"
//                                )
//                            }
//                        }
//                    }
//                }
//                item {
//                    Image(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(280.dp),
//                        painter = painterResource(id = R.drawable.cypresses),
//                        contentDescription = "cipreses"
//                    )
//                    Text(
//                        text = stringResource(id = R.string.paintTitle),
//                        fontSize = 32.sp,
//                        color = Color.Black,
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Center
//                    )
//                    Text(
//                        modifier = Modifier.padding(top = 16.dp),
//                        text = stringResource(id = R.string.paintDescription),
//                        color = Color.Black
//                    )
//                }
//            }
//        }
//    }
//}

