package com.example.ragla

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoriesScreen(
    onBack: () -> Unit,
    onCategoryClick: (String) -> Unit
) {

    // =========================================================
    // COLORES DE RAGLA
    // =========================================================

    val fondo = Color(0xFFF7F5FA)
    val morado = Color(0xFF5E35B1)
    val moradoOscuro = Color(0xFF4527A0)
    val moradoClaro = Color(0xFFEDE7F6)

    val textoPrincipal = Color(0xFF28242D)
    val textoSecundario = Color(0xFF77727C)

    // =========================================================
    // PANTALLA
    // =========================================================

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

        // =====================================================
        // ENCABEZADO
        // =====================================================

        Row(
            modifier = Modifier
                .fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            // BOTÓN REGRESAR

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        onBack()
                    },

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text = "‹",

                    fontSize = 32.sp,

                    color = morado,

                    textAlign =
                        TextAlign.Center
                )
            }

            Spacer(
                modifier =
                    Modifier.width(12.dp)
            )

            Column {

                Text(
                    text = "Categorías",

                    style = MaterialTheme
                        .typography
                        .headlineSmall,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        textoPrincipal
                )

                Text(
                    text =
                        "Políticas escolares",

                    style = MaterialTheme
                        .typography
                        .bodySmall,

                    color =
                        textoSecundario
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        // =====================================================
        // PRESENTACIÓN
        // =====================================================

        Card(
            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(20.dp),

            colors =
                CardDefaults.cardColors(
                    containerColor =
                        moradoClaro
                ),

            elevation =
                CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
        ) {

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(
                    text =
                        "Explora las políticas",

                    style = MaterialTheme
                        .typography
                        .titleLarge,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        moradoOscuro
                )

                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )

                Text(
                    text =
                        "Selecciona una categoría para consultar las políticas y reglamentos relacionados.",

                    style = MaterialTheme
                        .typography
                        .bodyMedium,

                    color =
                        textoPrincipal
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(22.dp)
        )

        // =====================================================
        // TÍTULO
        // =====================================================

        Text(
            text =
                "Selecciona un tema",

            style = MaterialTheme
                .typography
                .titleMedium,

            fontWeight =
                FontWeight.Bold,

            color =
                textoPrincipal
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        // =====================================================
        // ACADÉMICAS
        // =====================================================

        CategoryOption(
            icono = "▣",

            titulo =
                "Académicas",

            descripcion =
                "Evaluaciones, actividades y procesos académicos.",

            colorIcono =
                morado,

            fondoIcono =
                moradoClaro,

            onClick = {
                onCategoryClick(
                    "Académicas"
                )
            }
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        // =====================================================
        // ESTUDIANTES
        // =====================================================

        CategoryOption(
            icono = "◆",

            titulo =
                "Estudiantes",

            descripcion =
                "Derechos, obligaciones y servicios estudiantiles.",

            colorIcono =
                Color(0xFF1565C0),

            fondoIcono =
                Color(0xFFE3F2FD),

            onClick = {
                onCategoryClick(
                    "Estudiantes"
                )
            }
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        // =====================================================
        // CONDUCTA
        // =====================================================

        CategoryOption(
            icono = "✓",

            titulo =
                "Conducta",

            descripcion =
                "Normas de convivencia y comportamiento institucional.",

            colorIcono =
                Color(0xFF6A1B9A),

            fondoIcono =
                Color(0xFFF3E5F5),

            onClick = {
                onCategoryClick(
                    "Conducta"
                )
            }
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        // =====================================================
        // INSTITUCIONALES
        // =====================================================

        CategoryOption(
            icono = "▤",

            titulo =
                "Institucionales",

            descripcion =
                "Reglamentos y disposiciones institucionales.",

            colorIcono =
                Color(0xFF283593),

            fondoIcono =
                Color(0xFFE8EAF6),

            onClick = {
                onCategoryClick(
                    "Institucionales"
                )
            }
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        // =====================================================
        // PIE DE PANTALLA
        // =====================================================

        Text(
            text =
                "RAGLA • 2026",

            modifier =
                Modifier.fillMaxWidth(),

            style = MaterialTheme
                .typography
                .labelSmall,

            color =
                Color(0xFF99949C),

            textAlign =
                TextAlign.Center
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )
    }
}


// =================================================================
// TARJETA DE CATEGORÍA
// =================================================================

@Composable
fun CategoryOption(
    icono: String,
    titulo: String,
    descripcion: String,
    colorIcono: Color,
    fondoIcono: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape =
            RoundedCornerShape(18.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    Color.White
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation =
                    3.dp
            )
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(17.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            // =================================================
            // ICONO
            // =================================================

            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
                    .background(
                        fondoIcono
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text =
                        icono,

                    fontSize =
                        23.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        colorIcono
                )
            }

            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )

            // =================================================
            // INFORMACIÓN
            // =================================================

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        titulo,

                    style = MaterialTheme
                        .typography
                        .titleMedium,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Color(0xFF29252D)
                )

                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )

                Text(
                    text =
                        descripcion,

                    style = MaterialTheme
                        .typography
                        .bodySmall,

                    color =
                        Color(0xFF77727C)
                )
            }

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            // =================================================
            // FLECHA
            // =================================================

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        Color(0xFFF3EFF8)
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text =
                        "›",

                    fontSize =
                        25.sp,

                    color =
                        colorIcono,

                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}