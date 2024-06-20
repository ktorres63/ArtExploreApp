package com.danp.artexploreapp.GalleryArt.presentation.screens

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.danp.artexploreapp.ui.theme.PurpleGrey40
import com.danp.artexploreapp.util.navigation.Screens
import kotlinx.coroutines.launch


@Preview(showBackground = true)
@Composable
fun CanvaPreview() {
    MapMuseum(navController = rememberNavController())
    @Composable
    fun MapMuseum(navController: NavController) {
        Box(
            modifier = Modifier
                .background(PurpleGrey40)
                .fillMaxSize()
                .padding(top = 25.dp, start = 5.dp, end = 5.dp, bottom = 99.dp)
        ) {
            val textMeasurer = rememberTextMeasurer()
            val scope = rememberCoroutineScope()



            Canvas(modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { offset ->
                            scope.launch {//Completar con el mapa verdadero de la galería (karlo )
                                val height: Float = 1383 / 23F
                                val width: Float = 702 / 11f
                                Log.i("asd", "Círculo tocado en $offset")

                                //Height = 1383.0 /23
                                //Width = 702.0 /11

                                //Galeria 1
//                            X =  702.0... 255.27272
//                            Y= 0.0 ... 120.26087
                                if ((offset.x > 255 && offset.x <702) && (offset.y >0 && offset.y<120)) {
                                    //navController.navigate(Screens.ScreenSettings.route)
                                    Log.i("asdf", "Sala 1")
                                }


                            }
                        }
                    )

                }) {
                val customHeight = size.height / 23f
                val customWidth = size.width / 11f

                Log.i("qwe", "Height = ${size.height}")
                Log.i("qwe", "Width = ${size.width}")
                val treePath1 = pathToRectangles(
                    customWidth, customHeight, 4.5f, 18.5f, 7.5f, 21f,
                )
                val roomPath = pathToRectangles(
                    customWidth, customHeight, 4.2f, 14.5f, 9f, 17f
                )
                val chapelPath = pathToRectangles(
                    customWidth, customHeight, 4f, 10f, 11f, 7.5f
                )
                val gallery1Path = pathToRectangles(
                    customWidth, customHeight, 4f, 2f, 11f, 0f
                )
                Log.i(
                    "gallery 1",
                    "X =  ${customWidth * 11f}... ${customWidth * 4f}\nY= ${customHeight * 0f} ... ${customHeight * 2f}"
                )


                val gallery2Path = pathToRectangles(
                    customWidth, customHeight, 9f, 7.5f, 11f, 2f
                )
                val gallery3Path = pathToRectangles(
                    customWidth, customHeight, 4f, 7.5f, 9f, 5.5f
                )
                val unknownRoomPath = pathToRectangles(
                    customWidth, customHeight, 0f, 0f, 2f, 2f
                )
                val gallery4Path = pathToRectangles(
                    customWidth, customHeight, 0f, 2f, 2f, 6f
                )
                val gallery5Path = pathToRectangles(
                    customWidth, customHeight, 0f, 6f, 2f, 9f
                )
                val gallery6Path = pathToRectangles(
                    customWidth, customHeight, 9f, 17f, 11f, 10f
                )
                val lockRoomPath = pathToRectangles(
                    customWidth, customHeight, 0f, 10f, 2f, 17f
                )

                //Entrada
                withTransform({
                    translate(left = customWidth * 8f, top = customHeight * -15f)
                    rotate(degrees = 270F)
                }) { drawText(measuredText("Entrada", textMeasurer), color = Color.LightGray) }

                //Gallery 1 (border,rectangle and text)
                drawPath(gallery1Path, Color(0xFFDB073D))
                drawPath(gallery1Path, Color.Black, style = Stroke(width = 8f))
                translate(left = customWidth * 6.5f, top = customHeight * 0.5f) {
                    drawText(measuredText("Habitacion 1", textMeasurer))
                }

                //Gallery 2 (border,rectangle and text)
                drawPath(gallery2Path, Color(0xFFDAA507))
                drawPath(gallery2Path, Color.Black, style = Stroke(width = 8f))
                withTransform({
                    translate(left = customWidth * -5.7f, top = customHeight * -2f)
                    rotate(degrees = 90F)
                }) { drawText(measuredText("Habitacion 2", textMeasurer)) }

                //Gallery 3 (border,rectangle and text)
                drawPath(gallery3Path, Color(0xFF8EC7D2))
                drawPath(gallery3Path, Color.Black, style = Stroke(width = 8f))
                translate(left = customWidth * 5.5f, top = customHeight * 6f) {
                    drawText(measuredText("Habitacion 3", textMeasurer))
                }
                //Gallery 4 (border,rectangle and text)
                drawPath(gallery4Path, Color(0xFF0D6A87))
                drawPath(gallery4Path, Color.Black, style = Stroke(width = 8f))
                withTransform({
                    translate(left = customWidth * 5.8f, top = customHeight * -12.3f)
                    rotate(degrees = 270F)
                }) { drawText(measuredText("Habitacion 4", textMeasurer)) }

                //Gallery 5 (border,rectangle and text)
                drawPath(gallery5Path, Color(0xFF820024))
                drawPath(gallery5Path, Color.Black, style = Stroke(width = 8f))
                withTransform({
                    translate(left = customWidth * 5.8f, top = customHeight * -8.8f)
                    rotate(degrees = 270F)
                }) { drawText(measuredText("Habitacion 5", textMeasurer), color = Color.LightGray) }

                //Gallery 6 (border,rectangle and text)
                drawPath(gallery6Path, Color(0xFF00B8A3))
                drawPath(gallery6Path, Color.Black, style = Stroke(width = 8f))
                drawPath(gallery2Path, Color.Black, style = Stroke(width = 8f))
                withTransform({
                    translate(left = customWidth * -5.7f, top = customHeight * 6.8f)
                    rotate(degrees = 90F)
                }) { drawText(measuredText("Habitacion 6", textMeasurer)) }

                //Tree
                drawPath(treePath1, Color(0xff454545))
                drawPath(treePath1, Color.Black, style = Stroke(width = 8f))
                drawCircle(
                    color = Color(0xFF03C959),
                    radius = customWidth,
                    center = Offset(x = customWidth * 6f, y = customHeight * 19.7f)
                )
                translate(left = customWidth * 5.4f, top = customHeight * 19.3f) {
                    drawText(measuredText("Arbol", textMeasurer))
                }

                // Block Rooms
                drawPath(roomPath, Color(0xff454545))
                drawPath(roomPath, Color.Black, style = Stroke(width = 8f))
                translate(left = customWidth * 5.5f, top = customHeight * 15.3f) {
                    drawText(measuredText("Bloqueado", textMeasurer), Color.White)
                }

                drawPath(lockRoomPath, Color(0xff454545))
                drawPath(lockRoomPath, Color.Black, style = Stroke(width = 8f))
                withTransform({
                    translate(left = customWidth * 5.8f, top = customHeight * -2.7f)
                    rotate(degrees = 270F)
                }) { drawText(measuredText("Bloqueado", textMeasurer), color = Color.LightGray) }

                drawPath(unknownRoomPath, Color(0xff454545))
                drawPath(unknownRoomPath, Color.Black, style = Stroke(width = 8f))

                drawPath(chapelPath, Color(0xffD1BCE3))
                drawPath(chapelPath, Color.Black, style = Stroke(width = 8f))
                translate(left = customWidth * 6.8f, top = customHeight * 8.3f) {
                    drawText(measuredText("Capilla", textMeasurer))
                }

                //Tree 2
                drawCircle(
                    color = Color(0xFF03C959),
                    radius = customWidth,
                    center = Offset(x = customWidth * 6f, y = customHeight * 12.3f)
                )
                translate(left = customWidth * 5.3f, top = customHeight * 11.9f) {
                    drawText(measuredText("Arbol", textMeasurer))
                }

            }
        }
    }

    fun measuredText(txt: String, textMeasurer: TextMeasurer): TextLayoutResult {
        val measuredText =
            textMeasurer.measure(
                AnnotatedString(txt),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                overflow = TextOverflow.Ellipsis,

                )
        return measuredText
    }

    fun pathToRectangles(
        width: Float,
        height: Float,
        x1: Float,
        y1: Float,
        x3: Float,
        y3: Float,

        ): Path {
        //Using only two points to generate the rectangle
        val path = Path()
        path.moveTo(width * x1, height * y1)
        path.lineTo(width * x3, height * y1)
        path.lineTo(width * x3, height * y3)
        path.lineTo(width * x1, height * y3)
        path.close()

        return path
    }
}


@Composable
fun MapMuseum(navController: NavController) {
    Box(
        modifier = Modifier
            .background(PurpleGrey40)
            .fillMaxSize()
            .padding(top = 25.dp, start = 5.dp, end = 5.dp, bottom = 99.dp)
    ) {
        val textMeasurer = rememberTextMeasurer()
        val scope = rememberCoroutineScope()



        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        scope.launch {
                            val height: Float = 1383 / 23F
                            val width: Float = 702 / 11f
                            Log.i("asd", "Círculo tocado en $offset")

                            //Height = 1383.0 /23
                            //Width = 702.0 /11

                            //Galeria 1
//                            X =  702.0... 255.27272
//                            Y= 0.0 ... 120.26087
                            if ((offset.x > 255 && offset.x <702) && (offset.y >0 && offset.y<120)) {

                                navController.navigate(Screens.ScreenRoom1Map.route)

                            }


                        }
                    }
                )

            }) {
            val customHeight = size.height / 23f
            val customWidth = size.width / 11f

            Log.i("height", "Height = ${size.height}")
            Log.i("width", "Width = ${size.width}")
            val treePath1 = pathToRectangles(
                customWidth, customHeight, 4.5f, 18.5f, 7.5f, 21f,
            )
            val roomPath = pathToRectangles(
                customWidth, customHeight, 4.2f, 14.5f, 9f, 17f
            )
            val chapelPath = pathToRectangles(
                customWidth, customHeight, 4f, 10f, 11f, 7.5f
            )
            val gallery1Path = pathToRectangles(
                customWidth, customHeight, 4f, 2f, 11f, 0f
            )
            Log.i(
                "gallery 1",
                "X =  ${customWidth * 11f}... ${customWidth * 4f}\nY= ${customHeight * 0f} ... ${customHeight * 2f}"
            )


            val gallery2Path = pathToRectangles(
                customWidth, customHeight, 9f, 7.5f, 11f, 2f
            )
            val gallery3Path = pathToRectangles(
                customWidth, customHeight, 4f, 7.5f, 9f, 5.5f
            )
            val unknownRoomPath = pathToRectangles(
                customWidth, customHeight, 0f, 0f, 2f, 2f
            )
            val gallery4Path = pathToRectangles(
                customWidth, customHeight, 0f, 2f, 2f, 6f
            )
            val gallery5Path = pathToRectangles(
                customWidth, customHeight, 0f, 6f, 2f, 9f
            )
            val gallery6Path = pathToRectangles(
                customWidth, customHeight, 9f, 17f, 11f, 10f
            )
            val lockRoomPath = pathToRectangles(
                customWidth, customHeight, 0f, 10f, 2f, 17f
            )

            //Entrada
            withTransform({
                translate(left = customWidth * 8f, top = customHeight * -15f)
                rotate(degrees = 270F)
            }) { drawText(measuredText("Entrada", textMeasurer), color = Color.LightGray) }

            //Gallery 1 (border,rectangle and text)
            drawPath(gallery1Path, Color(0xFFDB073D))
            drawPath(gallery1Path, Color.Black, style = Stroke(width = 8f))
            translate(left = customWidth * 6.5f, top = customHeight * 0.5f) {
                drawText(measuredText("Habitacion 1", textMeasurer))
            }

            //Gallery 2 (border,rectangle and text)
            drawPath(gallery2Path, Color(0xFFDAA507))
            drawPath(gallery2Path, Color.Black, style = Stroke(width = 8f))
            withTransform({
                translate(left = customWidth * -5.7f, top = customHeight * -2f)
                rotate(degrees = 90F)
            }) { drawText(measuredText("Habitacion 2", textMeasurer)) }

            //Gallery 3 (border,rectangle and text)
            drawPath(gallery3Path, Color(0xFF8EC7D2))
            drawPath(gallery3Path, Color.Black, style = Stroke(width = 8f))
            translate(left = customWidth * 5.5f, top = customHeight * 6f) {
                drawText(measuredText("Habitacion 3", textMeasurer))
            }
            //Gallery 4 (border,rectangle and text)
            drawPath(gallery4Path, Color(0xFF0D6A87))
            drawPath(gallery4Path, Color.Black, style = Stroke(width = 8f))
            withTransform({
                translate(left = customWidth * 5.8f, top = customHeight * -12.3f)
                rotate(degrees = 270F)
            }) { drawText(measuredText("Habitacion 4", textMeasurer)) }

            //Gallery 5 (border,rectangle and text)
            drawPath(gallery5Path, Color(0xFF820024))
            drawPath(gallery5Path, Color.Black, style = Stroke(width = 8f))
            withTransform({
                translate(left = customWidth * 5.8f, top = customHeight * -8.8f)
                rotate(degrees = 270F)
            }) { drawText(measuredText("Habitacion 5", textMeasurer), color = Color.LightGray) }

            //Gallery 6 (border,rectangle and text)
            drawPath(gallery6Path, Color(0xFF00B8A3))
            drawPath(gallery6Path, Color.Black, style = Stroke(width = 8f))
            drawPath(gallery2Path, Color.Black, style = Stroke(width = 8f))
            withTransform({
                translate(left = customWidth * -5.7f, top = customHeight * 6.8f)
                rotate(degrees = 90F)
            }) { drawText(measuredText("Habitacion 6", textMeasurer)) }

            //Tree
            drawPath(treePath1, Color(0xff454545))
            drawPath(treePath1, Color.Black, style = Stroke(width = 8f))
            drawCircle(
                color = Color(0xFF03C959),
                radius = customWidth,
                center = Offset(x = customWidth * 6f, y = customHeight * 19.7f)
            )
            translate(left = customWidth * 5.4f, top = customHeight * 19.3f) {
                drawText(measuredText("Arbol", textMeasurer))
            }

            // Block Rooms
            drawPath(roomPath, Color(0xff454545))
            drawPath(roomPath, Color.Black, style = Stroke(width = 8f))
            translate(left = customWidth * 5.5f, top = customHeight * 15.3f) {
                drawText(measuredText("Bloqueado", textMeasurer), Color.White)
            }

            drawPath(lockRoomPath, Color(0xff454545))
            drawPath(lockRoomPath, Color.Black, style = Stroke(width = 8f))
            withTransform({
                translate(left = customWidth * 5.8f, top = customHeight * -2.7f)
                rotate(degrees = 270F)
            }) { drawText(measuredText("Bloqueado", textMeasurer), color = Color.LightGray) }

            drawPath(unknownRoomPath, Color(0xff454545))
            drawPath(unknownRoomPath, Color.Black, style = Stroke(width = 8f))

            drawPath(chapelPath, Color(0xffD1BCE3))
            drawPath(chapelPath, Color.Black, style = Stroke(width = 8f))
            translate(left = customWidth * 6.8f, top = customHeight * 8.3f) {
                drawText(measuredText("Capilla", textMeasurer))
            }

            //Tree 2
            drawCircle(
                color = Color(0xFF03C959),
                radius = customWidth,
                center = Offset(x = customWidth * 6f, y = customHeight * 12.3f)
            )
            translate(left = customWidth * 5.3f, top = customHeight * 11.9f) {
                drawText(measuredText("Arbol", textMeasurer))
            }

        }
    }
}

fun measuredText(txt: String, textMeasurer: TextMeasurer): TextLayoutResult {
    val measuredText =
        textMeasurer.measure(
            AnnotatedString(txt),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            overflow = TextOverflow.Ellipsis,

            )
    return measuredText
}

fun pathToRectangles(
    width: Float,
    height: Float,
    x1: Float,
    y1: Float,
    x3: Float,
    y3: Float,

    ): Path {
    //Using only two points to generate the rectangle
    val path = Path()
    path.moveTo(width * x1, height * y1)
    path.lineTo(width * x3, height * y1)
    path.lineTo(width * x3, height * y3)
    path.lineTo(width * x1, height * y3)
    path.close()

    return path
}