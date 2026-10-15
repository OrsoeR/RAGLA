package com.example.ragla
import androidx.compose.foundation.layout.width
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

private const val CLAVE_NOMBRE = "NOMBRE"
private const val CLAVE_CARRERA = "CARRERA"
private const val CLAVE_CORREO = "CORREO"
private const val CLAVE_FOTO = "FOTO"

@Composable
fun ProfileScreen(
    numeroControl: String,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    val fondo = Color(0xFFF7F5FA)
    val morado = Color(0xFF5E35B1)
    val moradoOscuro = Color(0xFF4527A0)
    val moradoClaro = Color(0xFFEDE7F6)
    val textoPrincipal = Color(0xFF29252D)
    val textoSecundario = Color(0xFF77727C)

    val preferencias = remember {
        context.getSharedPreferences(
            PREFERENCIAS_PERFIL,
            Context.MODE_PRIVATE
        )
    }

    var nombre by remember {
        mutableStateOf(
            preferencias.getString(
                CLAVE_NOMBRE,
                ""
            ) ?: ""
        )
    }

    var carrera by remember {
        mutableStateOf(
            preferencias.getString(
                CLAVE_CARRERA,
                ""
            ) ?: ""
        )
    }

    var correo by remember {
        mutableStateOf(
            preferencias.getString(
                CLAVE_CORREO,
                ""
            ) ?: ""
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

    var mensajeGuardado by remember {
        mutableStateOf("")
    }

    /*
     * Cargar información guardada
     */
    LaunchedEffect(Unit) {

        nombre = preferencias.getString(
            CLAVE_NOMBRE,
            ""
        ) ?: ""

        carrera = preferencias.getString(
            CLAVE_CARRERA,
            ""
        ) ?: ""

        correo = preferencias.getString(
            CLAVE_CORREO,
            ""
        ) ?: ""

        fotoUri = preferencias.getString(
            CLAVE_FOTO,
            ""
        ) ?: ""
    }

    /*
     * Selector de fotografía
     */
    val selectorImagen =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.OpenDocument()
        ) { uri: Uri? ->

            if (uri != null) {

                try {
                    context.contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                } catch (_: Exception) {
                }

                fotoUri = uri.toString()

                preferencias.edit()
                    .putString(
                        CLAVE_FOTO,
                        fotoUri
                    )
                    .apply()
            }
        }

    /*
     * Convertir URI guardada en Bitmap
     */
    val bitmap = remember(fotoUri) {

        if (fotoUri.isNotBlank()) {

            try {

                val uri = Uri.parse(fotoUri)

                context.contentResolver
                    .openInputStream(uri)
                    ?.use { inputStream ->
                        BitmapFactory.decodeStream(
                            inputStream
                        )
                    }

            } catch (_: Exception) {
                null
            }

        } else {
            null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
    ) {

        /*
         * ENCABEZADO
         */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 18.dp,
                    vertical = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        onBack()
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "‹",
                    fontSize = 32.sp,
                    color = morado
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Mi perfil",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = textoPrincipal
                )

                Text(
                    text = "Información del estudiante",
                    style = MaterialTheme.typography.bodySmall,
                    color = textoSecundario
                )
            }

            /*
             * MINI INDICADOR RAGLA
             */
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(morado),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "R",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        /*
         * CONTENIDO
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 18.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            /*
             * TARJETA DE PERFIL
             */
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    /*
                     * FOTO
                     */
                    if (bitmap != null) {

                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Foto de perfil",
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                    } else {

                        Box(
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                                .background(moradoClaro),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "R",
                                fontSize = 52.sp,
                                fontWeight = FontWeight.Bold,
                                color = morado
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Text(
                        text = if (nombre.isNotBlank()) {
                            nombre
                        } else {
                            "Estudiante RAGLA"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = textoPrincipal,
                        textAlign = TextAlign.Center
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "Número de control: $numeroControl",
                        style = MaterialTheme.typography.bodyMedium,
                        color = textoSecundario,
                        textAlign = TextAlign.Center
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Button(
                        onClick = {
                            selectorImagen.launch(
                                arrayOf("image/*")
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = morado
                        )
                    ) {

                        Text(
                            text = "Cambiar foto",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            /*
             * DATOS PERSONALES
             */
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Datos personales",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = textoPrincipal
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Actualiza la información que deseas mostrar en tu perfil.",
                        style = MaterialTheme.typography.bodySmall,
                        color = textoSecundario
                    )

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    /*
                     * NOMBRE
                     */
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = {
                            nombre = it
                            mensajeGuardado = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Nombre completo")
                        },
                        placeholder = {
                            Text("Escribe tu nombre")
                        },
                        singleLine = true
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    /*
                     * CARRERA
                     */
                    OutlinedTextField(
                        value = carrera,
                        onValueChange = {
                            carrera = it
                            mensajeGuardado = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Carrera")
                        },
                        placeholder = {
                            Text("Ej. Ingeniería en Sistemas")
                        },
                        singleLine = true
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    /*
                     * CORREO
                     */
                    OutlinedTextField(
                        value = correo,
                        onValueChange = {
                            correo = it
                            mensajeGuardado = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Correo electrónico")
                        },
                        placeholder = {
                            Text("ejemplo@correo.com")
                        },
                        singleLine = true
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    /*
                     * NÚMERO DE CONTROL
                     */
                    OutlinedTextField(
                        value = numeroControl,
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Número de control")
                        },
                        singleLine = true,
                        readOnly = true
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            /*
             * GUARDAR
             */
            Button(
                onClick = {

                    preferencias.edit()
                        .putString(
                            CLAVE_NOMBRE,
                            nombre.trim()
                        )
                        .putString(
                            CLAVE_CARRERA,
                            carrera.trim()
                        )
                        .putString(
                            CLAVE_CORREO,
                            correo.trim()
                        )
                        .putString(
                            CLAVE_FOTO,
                            fotoUri
                        )
                        .apply()

                    mensajeGuardado =
                        "✓ Cambios guardados correctamente"
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = morado
                ),
                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "Guardar cambios",
                    fontWeight = FontWeight.Bold
                )
            }

            /*
             * MENSAJE DE GUARDADO
             */
            if (mensajeGuardado.isNotBlank()) {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = mensajeGuardado,
                    color = morado,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            HorizontalDivider(
                color = Color(0xFFE0DDE3)
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            /*
             * INFORMACIÓN
             */
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = moradoClaro
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Sobre tu perfil",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = moradoOscuro
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Los datos de tu perfil se guardan localmente en el dispositivo para que permanezcan disponibles cuando vuelvas a abrir RAGLA.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = textoPrincipal
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            /*
             * CERRAR SESIÓN
             */
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onLogout()
                    },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFEBEE)
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Cerrar sesión",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC62828)
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Saldrás de tu sesión actual y tendrás que ingresar nuevamente para acceder a RAGLA.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = textoPrincipal
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Button(
                onClick = {
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC62828)
                ),
                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "Cerrar sesión",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "RAGLA • 2026",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF99949C),
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}