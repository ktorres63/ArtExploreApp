package com.danp.lab05_danp_canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.danp.lab05_danp_canvas.presentation.screen.Gallery
import com.danp.lab05_danp_canvas.presentation.viewmodel.GalleryViewModel
import com.danp.lab05_danp_canvas.ui.theme.Lab05_DANP_CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = GalleryViewModel()

            Lab05_DANP_CanvasTheme {
                Gallery(viewModel = viewModel,context = applicationContext,)
            }
        }
    }
}




