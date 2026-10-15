package com.example.ragla

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PolicyDetailScreen(
    policy: Policy,
    isFavorite: Boolean,
    onBack: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    val fondo = Color(0xFFF7F5FA)
    val morado = Color(0xFF5E35B1)
    val moradoOscuro = Color(0xFF4527A0)
    val moradoClaro = Color(0xFFEDE7F6)
    val textoPrincipal = Color(0xFF29252D)
    val textoSecundario = Color(0xFF77727C)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .verticalScroll(rememberScrollState())
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
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextButton(
                onClick = onBack
            ) {
                Text(
                    text = "‹",
                    fontSize = 32.sp,
                    color = morado
                )
            }

            Spacer(
                modifier = Modifier.width(4.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Detalle de política",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = textoPrincipal
                )

                Text(
                    text = "RAGLA • Políticas escolares",
                    style = MaterialTheme.typography.bodySmall,
                    color = textoSecundario
                )
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // ==================================================
        // TÍTULO
        // ==================================================

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = policy.titulo,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = textoPrincipal
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                // Categoría

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(moradoClaro),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "R",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = morado
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    Column {

                        Text(
                            text = "Categoría",
                            style = MaterialTheme.typography.labelSmall,
                            color = textoSecundario
                        )

                        Text(
                            text = policy.categoria,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = moradoOscuro
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // ==================================================
        // RESUMEN
        // ==================================================

        Text(
            text = "Resumen",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = textoPrincipal
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = moradoClaro
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            Text(
                text = policy.descripcion,
                modifier = Modifier.padding(18.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = textoPrincipal
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // ==================================================
        // INFORMACIÓN COMPLETA
        // ==================================================

        Text(
            text = "Información",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = textoPrincipal
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Text(
                text = policy.contenido,
                modifier = Modifier.padding(18.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = textoPrincipal
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // ==================================================
        // FUENTE
        // ==================================================

        Text(
            text = "Fuente",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = textoPrincipal
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = policy.fuente,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoPrincipal
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Contenido resumido para fines académicos.",
                    style = MaterialTheme.typography.bodySmall,
                    color = textoSecundario
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // ==================================================
        // FAVORITOS
        // ==================================================

        Card(
            modifier = Modifier.fillMaxWidth(),
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = if (isFavorite)
                            "★ Guardada en favoritos"
                        else
                            "☆ Guardar en favoritos",
                        fontWeight = FontWeight.Bold,
                        color = if (isFavorite)
                            morado
                        else
                            textoPrincipal
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = if (isFavorite)
                            "Puedes encontrarla posteriormente en Favoritos."
                        else
                            "Guarda esta política para consultarla rápidamente.",
                        style = MaterialTheme.typography.bodySmall,
                        color = textoSecundario
                    )
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Button(
                    onClick = onFavoriteClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = morado
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {

                    Text(
                        text = if (isFavorite)
                            "Quitar"
                        else
                            "Guardar",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // ==================================================
        // REGRESAR
        // ==================================================

        TextButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Volver a las políticas",
                color = morado,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "RAGLA • 2026",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF99949C),
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )
    }
}