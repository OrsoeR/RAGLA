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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun PoliciesScreen(
    categoria: String,
    onBack: () -> Unit,
    onPolicyClick: (Policy) -> Unit
) {

    val fondo = Color(0xFFF7F5FA)
    val morado = Color(0xFF5E35B1)
    val moradoOscuro = Color(0xFF4527A0)
    val moradoClaro = Color(0xFFEDE7F6)
    val textoPrincipal = Color(0xFF29252D)
    val textoSecundario = Color(0xFF77727C)

    val politicas = obtenerPoliticas(categoria)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
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

            Box(
                modifier = Modifier
                    .size(42.dp)
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
                    color = morado,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = categoria,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = textoPrincipal
                )

                Text(
                    text = "Políticas escolares",
                    style = MaterialTheme.typography.bodySmall,
                    color = textoSecundario
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // ==================================================
        // TARJETA INTRODUCTORIA
        // ==================================================

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = moradoClaro
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Políticas disponibles",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = moradoOscuro
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Consulta la información relacionada con esta categoría y selecciona una política para ver sus detalles.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textoPrincipal
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // ==================================================
        // CONTADOR
        // ==================================================

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${politicas.size} política(s)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = textoPrincipal
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Selecciona una",
                style = MaterialTheme.typography.bodySmall,
                color = textoSecundario
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // ==================================================
        // LISTA
        // ==================================================

        if (politicas.isEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "No hay políticas disponibles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = textoPrincipal,
                        textAlign = TextAlign.Center
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "No se encontraron políticas para esta categoría.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = textoSecundario,
                        textAlign = TextAlign.Center
                    )
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(politicas) { politica ->

                    PolicyCard(
                        policy = politica,
                        morado = morado,
                        moradoClaro = moradoClaro,
                        textoPrincipal = textoPrincipal,
                        textoSecundario = textoSecundario,
                        onClick = {
                            onPolicyClick(politica)
                        }
                    )
                }

                item {

                    Spacer(
                        modifier = Modifier.height(8.dp)
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
        }
    }
}


// ======================================================
// TARJETA DE POLÍTICA
// ======================================================

@Composable
fun PolicyCard(
    policy: Policy,
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

        Column(
            modifier = Modifier.padding(17.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                // Número de política

                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(moradoClaro),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = policy.id.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
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
                        text = policy.titulo,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = textoPrincipal
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = policy.categoria,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = morado
                    )
                }

                Text(
                    text = "›",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = morado
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = policy.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                color = textoSecundario
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Consultar información",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = morado
            )
        }
    }
}


// ======================================================
// LISTA DE POLÍTICAS
// ======================================================

fun obtenerPoliticas(categoria: String): List<Policy> {

    val politicas = listOf(

        // ==================================================
        // ACADÉMICAS
        // ==================================================

        Policy(
            id = 1,
            titulo = "Inscripción y reinscripción",
            categoria = "Académicas",
            descripcion = "Información sobre los procesos de inscripción y reinscripción.",
            contenido = "Para mantener la condición de estudiante es necesario realizar los procesos de inscripción o reinscripción correspondientes durante los periodos establecidos por la institución.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículo 4."
        ),

        Policy(
            id = 2,
            titulo = "Evaluación académica",
            categoria = "Académicas",
            descripcion = "Información relacionada con la evaluación de las actividades académicas.",
            contenido = "El estudiante debe cumplir con las actividades académicas y los criterios de evaluación establecidos para las asignaturas que cursa.",
            fuente = "Lineamientos académicos del TecNM."
        ),

        Policy(
            id = 3,
            titulo = "Asistencia y actividades académicas",
            categoria = "Académicas",
            descripcion = "Responsabilidades relacionadas con las clases y actividades académicas.",
            contenido = "El estudiante debe asistir y participar en las actividades académicas correspondientes y cumplir con los compromisos de su trayectoria escolar.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículo 7."
        ),

        // ==================================================
        // ESTUDIANTES
        // ==================================================

        Policy(
            id = 4,
            titulo = "Derechos de los estudiantes",
            categoria = "Estudiantes",
            descripcion = "Principales derechos de los estudiantes.",
            contenido = "Los estudiantes tienen derecho a recibir los servicios educativos, utilizar las instalaciones, recibir un trato digno y respetuoso y participar en actividades académicas, culturales y deportivas.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículo 6."
        ),

        Policy(
            id = 5,
            titulo = "Obligaciones de los estudiantes",
            categoria = "Estudiantes",
            descripcion = "Responsabilidades que deben cumplir los estudiantes.",
            contenido = "Los estudiantes deben respetar a la comunidad escolar, conocer y cumplir las disposiciones institucionales, cumplir con su plan de estudios y cuidar las instalaciones.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículo 7."
        ),

        // ==================================================
        // CONDUCTA
        // ==================================================

        Policy(
            id = 6,
            titulo = "Conductas prohibidas",
            categoria = "Conducta",
            descripcion = "Conductas que pueden afectar la convivencia escolar.",
            contenido = "Se deben evitar conductas como violencia, intimidación, acoso, discriminación, falsificación de documentos, daños a bienes institucionales y otras acciones contrarias a la normativa.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículo 8."
        ),

        Policy(
            id = 7,
            titulo = "Respeto y convivencia",
            categoria = "Conducta",
            descripcion = "Normas para mantener una convivencia respetuosa.",
            contenido = "Todos los integrantes de la comunidad escolar deben mantener una conducta respetuosa y evitar cualquier forma de violencia, discriminación o acoso.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículos 5, 7 y 8."
        ),

        // ==================================================
        // INSTITUCIONALES
        // ==================================================

        Policy(
            id = 8,
            titulo = "Sanciones",
            categoria = "Institucionales",
            descripcion = "Medidas que pueden aplicarse ante el incumplimiento de las disposiciones.",
            contenido = "Dependiendo de la gravedad de la conducta, pueden aplicarse diferentes medidas disciplinarias establecidas por la normativa institucional.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículo 9."
        ),

        Policy(
            id = 9,
            titulo = "Procedimiento sancionador",
            categoria = "Institucionales",
            descripcion = "Proceso para atender posibles incumplimientos al reglamento.",
            contenido = "La institución cuenta con un procedimiento para analizar los hechos, escuchar a las partes involucradas y determinar las medidas correspondientes.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículo 14."
        ),

        Policy(
            id = 10,
            titulo = "Recurso de revisión",
            categoria = "Institucionales",
            descripcion = "Mecanismo para solicitar la revisión de una resolución.",
            contenido = "El estudiante puede utilizar el recurso de revisión en los términos establecidos por el reglamento cuando considere necesario solicitar la revisión de una resolución.",
            fuente = "Reglamento de Estudiantes del TecNM — Artículos 15 al 17."
        )
    )

    return politicas.filter { politica ->
        politica.categoria == categoria
    }
}