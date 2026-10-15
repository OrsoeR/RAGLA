package com.example.ragla

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val PREFERENCIAS_PERFIL = "RAGLA_PERFIL"
private const val CLAVE_FOTO = "FOTO"

@Composable
fun HomeScreen(
    onCategoriesClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    val context = LocalContext.current

    // ==================================================
    // COLORES
    // ==================================================

    val fondo = Color(0xFFF7F5FA)
    val morado = Color(0xFF5E35B1)
    val moradoOscuro = Color(0xFF4527A0)
    val moradoClaro = Color(0xFFEDE7F6)
    val textoPrincipal = Color(0xFF29252D)
    val textoSecundario = Color(0xFF77727C)

    // ==================================================
    // RECUPERAR FOTO DE PERFIL
    // ==================================================

    val preferencias = remember {
        context.getSharedPreferences(
            PREFERENCIAS_PERFIL,
            Context.MODE_PRIVATE
        )
    }

    var fotoUri by remember {
        mutableStateOf(
            preferencias.getString(
                CLAVE_FOTO,
                ""
            ) ?: ""
        )
    }

    // ==================================================
    // CONVERTIR FOTO A BITMAP
    // ==================================================

    val bitmap = remember(fotoUri) {

        if (fotoUri.isNotBlank()) {

            try {

                val uri = Uri.parse(fotoUri)

                context.contentResolver
                    .openInputStream(uri)
                    ?.use { inputStream ->
                        BitmapFactory.decodeStream(inputStream)
                    }

            } catch (_: Exception) {

                null
            }

        } else {

            null
        }
    }

    // ==================================================
    // PANTALLA PRINCIPAL
    // ==================================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
    ) {

        // ==================================================
        // CONTENIDO
        // ==================================================

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 18.dp,
                    vertical = 16.dp
                )
        ) {

            // ==================================================
            // ENCABEZADO
            // ==================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "RAGLA",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = morado
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = "Políticas Escolares",
                        style = MaterialTheme.typography.bodyLarge,
                        color = textoSecundario
                    )
                }

                // ==================================================
                // FOTO / PERFIL
                // ==================================================

                Card(
                    modifier = Modifier
                        .clickable {
                            onProfileClick()
                        },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = moradoClaro
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        if (bitmap != null) {

                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "Foto de perfil",
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                        } else {

                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = "👤",
                                    fontSize = 24.sp
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(2.dp)
                        )

                        Text(
                            text = "Perfil",
                            fontWeight = FontWeight.Bold,
                            color = morado
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // ==================================================
            // SALUDO
            // ==================================================

            Text(
                text = "Hola 👋",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = textoPrincipal
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Consulta las políticas escolares",
                style = MaterialTheme.typography.bodyLarge,
                color = textoSecundario
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // ==================================================
            // BUSCAR POLÍTICA
            // ==================================================

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSearchClick()
                    },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = morado
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "🔎 Buscar una política",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier.height(7.dp)
                    )

                    Text(
                        text = "Encuentra rápidamente información por palabra clave, tema o categoría.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "Toca aquí para buscar →",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // ==================================================
            // EXPLORAR POLÍTICAS
            // ==================================================

            Text(
                text = "Explorar políticas",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = textoPrincipal
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Consulta la información organizada por tema.",
                style = MaterialTheme.typography.bodyMedium,
                color = textoSecundario
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // ==================================================
            // ACADÉMICAS
            // ==================================================

            CategoryCard(
                titulo = "Académicas",
                descripcion = "Evaluaciones, actividades y procesos académicos.",
                icono = "▣",
                morado = morado,
                moradoClaro = moradoClaro,
                textoPrincipal = textoPrincipal,
                textoSecundario = textoSecundario,
                onClick = {
                    onCategoriesClick()
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // ==================================================
            // ESTUDIANTES
            // ==================================================

            CategoryCard(
                titulo = "Estudiantes",
                descripcion = "Derechos, obligaciones y servicios estudiantiles.",
                icono = "◆",
                morado = morado,
                moradoClaro = moradoClaro,
                textoPrincipal = textoPrincipal,
                textoSecundario = textoSecundario,
                onClick = {
                    onCategoriesClick()
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // ==================================================
            // CONDUCTA
            // ==================================================

            CategoryCard(
                titulo = "Conducta",
                descripcion = "Normas de convivencia y comportamiento institucional.",
                icono = "✓",
                morado = morado,
                moradoClaro = moradoClaro,
                textoPrincipal = textoPrincipal,
                textoSecundario = textoSecundario,
                onClick = {
                    onCategoriesClick()
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // ==================================================
            // INSTITUCIONALES
            // ==================================================

            CategoryCard(
                titulo = "Institucionales",
                descripcion = "Sanciones, procedimientos y disposiciones institucionales.",
                icono = "□",
                morado = morado,
                moradoClaro = moradoClaro,
                textoPrincipal = textoPrincipal,
                textoSecundario = textoSecundario,
                onClick = {
                    onCategoriesClick()
                }
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // ==================================================
            // PIE
            // ==================================================

            Text(
                text = "RAGLA • 2026",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF99949C),
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }

        // ==================================================
        // MENÚ INFERIOR
        // ==================================================

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 22.dp,
                topEnd = 22.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // ==================================================
                // INICIO
                // ==================================================

                BottomNavigationItem(
                    icono = "⌂",
                    texto = "Inicio",
                    seleccionado = true,
                    morado = morado
                )

                // ==================================================
                // BUSCAR
                // ==================================================

                BottomNavigationItem(
                    icono = "⌕",
                    texto = "Buscar",
                    seleccionado = false,
                    morado = morado,
                    onClick = {
                        onSearchClick()
                    }
                )

                // ==================================================
                // FAVORITOS
                // ==================================================

                BottomNavigationItem(
                    icono = "★",
                    texto = "Favoritos",
                    seleccionado = false,
                    morado = morado,
                    onClick = {
                        onFavoritesClick()
                    }
                )
            }
        }
    }
}

// ======================================================
// TARJETA DE CATEGORÍA
// ======================================================

@Composable
fun CategoryCard(
    titulo: String,
    descripcion: String,
    icono: String,
    morado: Color,
    moradoClaro: Color,
    textoPrincipal: Color,
    textoSecundario: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(moradoClaro),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = icono,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = morado
                )
            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = textoPrincipal
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoSecundario
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "›",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = morado
            )
        }
    }
}

// ======================================================
// ELEMENTO DEL MENÚ INFERIOR
// ======================================================

@Composable
fun BottomNavigationItem(
    icono: String,
    texto: String,
    seleccionado: Boolean,
    morado: Color,
    onClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 18.dp,
                vertical = 4.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = icono,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = if (seleccionado) {
                morado
            } else {
                Color(0xFF77727C)
            }
        )

        Text(
            text = texto,
            fontSize = 13.sp,
            fontWeight = if (seleccionado) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            color = if (seleccionado) {
                morado
            } else {
                Color(0xFF77727C)
            }
        )
    }
}