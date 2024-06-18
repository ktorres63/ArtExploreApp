package com.danp.lab05_danp_canvas.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GalleryViewModel : ViewModel(){
    var showDialog2 by  mutableStateOf(false)
        private set
    var showDialog1 by  mutableStateOf(false)
        private set
    var circlePosition by mutableStateOf(Offset(300f, 300f))
        private set


    fun onChangeShowDialog(newShowDialog: Boolean){
        showDialog1 = newShowDialog
        showDialog2 = newShowDialog
    }
    fun getRandomPosition(screenWidth: Float, screenHeight: Float, circleRadius: Float): Offset {
        val newX = (Random.nextFloat() * (screenWidth - 2 * circleRadius)) + circleRadius
        val newY = (Random.nextFloat() * (screenHeight - 2 * circleRadius)) + circleRadius
        return Offset(newX, newY)
    }


    fun onClickIconBox1() {
        showDialog1 = true
    }
    fun onClickIconBox2() {
        showDialog2 = true
    }

    fun setNewPositionCircle(newPosition: Offset) {
        circlePosition = newPosition
    }


}
