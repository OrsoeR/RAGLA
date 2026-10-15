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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen(
    onBack: () -> Unit,
    onPolicyClick: (Policy) -> Unit,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {

    /*
     * COLORES DE RAGLA
     */
    val fondo = Color(0xFFF7F5FA)
    val morado = Color(0xFF5E35B1)
    val moradoOscuro = Color(0xFF4527A0)
    val moradoClaro = Color(0xFFEDE7F6)
    val textoPrincipal = Color(0xFF29252D)
    val textoSecundario = Color(0xFF77727C)

    /*
     * TEXTO DE BÚSQUEDA
     */
    var textoBusqueda by remember {
        mutableStateOf("")
    }

    /*
     * OBTENER TODAS LAS POLÍTICAS
     */
    val todasLasPoliticas = obtenerTodasLasPoliticas()

    /*
     * FILTRAR RESULTADOS
     */
    val resultados = todasLasPoliticas.filter { politica ->

        politica.titulo.contains(
            textoBusqueda,
            ignoreCase = true
        ) ||

                politica.descripcion.contains(
                    textoBusqueda,
                    ignoreCase = true
                ) ||

                politica.contenido.contains(
                    textoBusqueda,
                    ignoreCase = true
                ) ||

                politica.categoria.contains(
                    textoBusqueda,
                    ignoreCase = true
                )
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
                    text = "Buscar",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = textoPrincipal
                )

                Text(
                    text = "Encuentra una política escolar",
                    style = MaterialTheme.typography.bodySmall,
                    color = textoSecundario
                )
            }

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
                .padding(
                    horizontal = 18.dp
                )
        ) {

            /*
             * TARJETA INTRODUCTORIA
             */
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
                        text = "Consulta las políticas",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = moradoOscuro
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Busca por nombre, categoría o palabras relacionadas con el contenido de una política.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = textoPrincipal
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            /*
             * CAMPO DE BÚSQUEDA
             */
            OutlinedTextField(
                value = textoBusqueda,

                onValueChange = {
                    textoBusqueda = it
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Buscar una política")
                },

                placeholder = {
                    Text("Ej. inscripción, derechos, sanciones")
                },

                singleLine = true,

                shape = RoundedCornerShape(16.dp)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            /*
             * ESTADO DE BÚSQUEDA
             */
            if (textoBusqueda.isBlank()) {

                /*
                 * SUGERENCIAS
                 */
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
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "¿Qué puedes buscar?",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = textoPrincipal
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            SearchSuggestion(
                                texto = "Inscripción",
                                moradoClaro = moradoClaro,
                                morado = morado,
                                onClick = {
                                    textoBusqueda = "inscripción"
                                }
                            )

                            SearchSuggestion(
                                texto = "Derechos",
                                moradoClaro = moradoClaro,
                                morado = morado,
                                onClick = {
                                    textoBusqueda = "derechos"
                                }
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            SearchSuggestion(
                                texto = "Conducta",
                                moradoClaro = moradoClaro,
                                morado = morado,
                                onClick = {
                                    textoBusqueda = "conducta"
                                }
                            )

                            SearchSuggestion(
                                texto = "Sanciones",
                                moradoClaro = moradoClaro,
                                morado = morado,
                                onClick = {
                                    textoBusqueda = "sanciones"
                                }
                            )
                        }
                    }
                }

            } else if (resultados.isEmpty()) {

                /*
                 * SIN RESULTADOS
                 */
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

                        Box(
                            modifier = Modifier
                                .size(58.dp)
                                .clip(CircleShape)
                                .background(moradoClaro),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "?",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = morado
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Text(
                            text = "No encontramos resultados",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = textoPrincipal,
                            textAlign = TextAlign.Center
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "Prueba con otra palabra como inscripción, derechos, conducta o sanciones.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = textoSecundario,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            } else {

                /*
                 * RESULTADOS
                 */
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Resultados",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = textoPrincipal
                    )

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "${resultados.size} encontrada(s)",
                        style = MaterialTheme.typography.bodySmall,
                        color = textoSecundario
                    )
                }

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(resultados) { politica ->

                        SearchPolicyCard(
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
}


/*
 * ======================================================
 * TARJETA DE SUGERENCIA
 * ======================================================
 */

@Composable
fun SearchSuggestion(
    texto: String,
    moradoClaro: Color,
    morado: Color,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(moradoClaro)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 14.dp,
                vertical = 9.dp
            )
    ) {

        Text(
            text = texto,
            color = morado,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    }
}


/*
 * ======================================================
 * TARJETA DE RESULTADO
 * ======================================================
 */

@Composable
fun SearchPolicyCard(
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
                        fontWeight = FontWeight.Bold,
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


/*
 * ======================================================
 * OBTENER TODAS LAS POLÍTICAS
 * ======================================================
 */

fun obtenerTodasLasPoliticas(): List<Policy> {

    return obtenerPoliticas("Académicas") +
            obtenerPoliticas("Estudiantes") +
            obtenerPoliticas("Conducta") +
            obtenerPoliticas("Institucionales")
}