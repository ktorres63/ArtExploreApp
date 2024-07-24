package com.danp.artexploreapp.home.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danp.artexploreapp.paiting.presentation.PaintingCard
import com.danp.artexploreapp.paiting.presentation.PaintingsViewModel
import com.danp.artexploreapp.ui.theme.PrimaryColor
import com.danp.artexploreapp.ui.theme.SecondaryColor
import com.danp.artexploreapp.util.MyTopBar


@Composable
fun Home(navController: NavController, paintingsViewModel: PaintingsViewModel) {
    val agentsData = paintingsViewModel.agentsData.collectAsState()
    val isLoading = paintingsViewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { MyTopBar(navController = navController, "Home", true) }
    ) { ip ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ip)
        ) {
            if (isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = SecondaryColor)
                Log.i("CARGANDO ..","")
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalItemSpacing = 10.dp
                ) {
                    items(agentsData.value) { painting ->
                        PaintingCard(navController,painting = painting)
                        Log.i("PRINT: ", "${agentsData.value[0].id}")
                    }

                }
            }
        }
    }
}
