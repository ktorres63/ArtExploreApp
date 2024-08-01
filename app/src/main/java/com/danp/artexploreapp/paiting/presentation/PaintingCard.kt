import android.content.Context
import android.media.AudioManager
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
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

    var player by remember { mutableStateOf<ExoPlayer?>(null) }
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) } // Estado para el diálogo

    LaunchedEffect(Unit) {
        player = ExoPlayer.Builder(context).build()
    }

    DisposableEffect(Unit) {
        onDispose { player?.release() }
    }

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
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
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
                            onClick = {
                                if (areHeadphonesConnected(context)) {
                                    // Reproduce el audio
                                    painting.audio.let { audioUrl ->
                                        val mediaItem = MediaItem.fromUri(Uri.parse(audioUrl))
                                        player?.setMediaItem(mediaItem)
                                        player?.prepare()
                                        player?.play()
                                    }
                                } else {
                                    // Muestra el diálogo solicitando conectar audífonos
                                    showDialog = true
                                }
                            },
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

    // Diálogo persistente para conectar audífonos
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* No hacer nada al cerrar el diálogo */ },
            title = { Text("Conectar Audífonos") },
            text = { Text("Por favor, conecta tus audífonos para poder reproducir el audio.") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false // Cierra el diálogo
                }) {
                    Text("Aceptar")
                }
            }
        )
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
        Text("Spanish")
    }
}

fun areHeadphonesConnected(context: Context): Boolean {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    return audioManager.isWiredHeadsetOn || audioManager.isBluetoothA2dpOn
}