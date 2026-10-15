package com.example.ragla

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesScreen(
    favoritos: List<Policy>,
    onBack: () -> Unit,
    onPolicyClick: (Policy) -> Unit,
    onRemoveFavorite: (Policy) -> Unit,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit
) {

    Scaffold(
        bottomBar = {

            // ==================================================
            // MENÚ INFERIOR
            // ==================================================

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // ==================================================
                    // INICIO
                    // ==================================================

                    Column(
                        modifier = Modifier
                            .clickable {
                                onHomeClick()
                            }
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "⌂",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = "Inicio"
                        )
                    }

                    // ==================================================
                    // BUSCAR
                    // ==================================================

                    Column(
                        modifier = Modifier
                            .clickable {
                                onSearchClick()
                            }
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "🔎",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = "Buscar"
                        )
                    }

                    // ==================================================
                    // FAVORITOS
                    // ==================================================

                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "★",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "Favoritos",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            // ==================================================
            // REGRESAR
            // ==================================================

            TextButton(
                onClick = onBack
            ) {
                Text(
                    text = "← Regresar"
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // ==================================================
            // TÍTULO
            // ==================================================

            Text(
                text = "Mis favoritos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Consulta las políticas que has guardado."
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // ==================================================
            // SIN FAVORITOS
            // ==================================================

            if (favoritos.isEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "☆ No tienes favoritos",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Cuando guardes una política aparecerá aquí."
                        )
                    }
                }

            } else {

                // ==================================================
                // CONTADOR
                // ==================================================

                Text(
                    text = "${favoritos.size} política(s) guardada(s)",
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                // ==================================================
                // LISTA
                // ==================================================

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        items = favoritos,
                        key = {
                            it.id
                        }
                    ) { politica ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onPolicyClick(
                                        politica
                                    )
                                }
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    text = "★ ${politica.titulo}",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier = Modifier.height(6.dp)
                                )

                                Text(
                                    text = "📚 ${politica.categoria}",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Medium
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = politica.descripcion
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                TextButton(
                                    onClick = {
                                        onRemoveFavorite(
                                            politica
                                        )
                                    }
                                ) {

                                    Text(
                                        text = "Quitar de favoritos"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}