package com.danp.artexploreapp.home.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danp.artexploreapp.paiting.presentation.PaintingCard
import com.danp.artexploreapp.paiting.presentation.PaintingsViewModel
import com.danp.artexploreapp.util.MyTopBar


@Composable
fun Home(navController: NavController, paintingsViewModel: PaintingsViewModel) {
    val agentsData = paintingsViewModel.agentsData.collectAsState()

    Scaffold(
        topBar = { MyTopBar(navController = navController, "Home", true) }
    ) { ip ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ip)
        ) {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp
            ) {
                items(agentsData.value) { painting ->
                    PaintingCard(painting = painting)
                }

            }
        }
    }
}
