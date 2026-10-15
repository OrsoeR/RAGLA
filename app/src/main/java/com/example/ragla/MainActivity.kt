package com.example.ragla

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.ragla.ui.theme.RAGLATheme

// ======================================================
// PREFERENCIAS
// ======================================================

private const val NOMBRE_PREFERENCIAS =
    "RAGLA_PREFERENCIAS"

private const val CLAVE_FAVORITOS =
    "FAVORITOS"

private const val CLAVE_SESION =
    "SESION_ACTIVA"

private const val CLAVE_CONTROL =
    "NUMERO_CONTROL"

// ======================================================
// GUARDAR FAVORITOS
// ======================================================

fun guardarFavoritos(
    context: Context,
    favoritos: List<Policy>
) {

    val preferencias =
        context.getSharedPreferences(
            NOMBRE_PREFERENCIAS,
            Context.MODE_PRIVATE
        )

    val ids =
        favoritos
            .map {
                it.id.toString()
            }
            .toSet()

    preferencias
        .edit()
        .putStringSet(
            CLAVE_FAVORITOS,
            ids
        )
        .apply()
}

// ======================================================
// CARGAR FAVORITOS
// ======================================================

fun cargarFavoritos(
    context: Context
): Set<Int> {

    val preferencias =
        context.getSharedPreferences(
            NOMBRE_PREFERENCIAS,
            Context.MODE_PRIVATE
        )

    val idsGuardados =
        preferencias.getStringSet(
            CLAVE_FAVORITOS,
            emptySet()
        ) ?: emptySet()

    return idsGuardados
        .mapNotNull {
            it.toIntOrNull()
        }
        .toSet()
}

// ======================================================
// ACTIVIDAD PRINCIPAL
// ======================================================

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(
            savedInstanceState
        )

        setContent {

            RAGLATheme {

                RAGLAApp()
            }
        }
    }
}

// ======================================================
// APLICACIÓN
// ======================================================

@Composable
fun RAGLAApp() {

    val context =
        LocalContext.current

    // ==================================================
    // COMPROBAR SESIÓN
    // ==================================================

    val preferenciasSesion =
        context.getSharedPreferences(
            NOMBRE_PREFERENCIAS,
            Context.MODE_PRIVATE
        )

    val sesionActiva =
        preferenciasSesion.getBoolean(
            CLAVE_SESION,
            false
        )

    var pantalla by remember {

        mutableStateOf(
            if (sesionActiva)
                "home"
            else
                "login"
        )
    }

    // ==================================================
    // NÚMERO DE CONTROL
    // ==================================================

    var numeroControl by remember {

        mutableStateOf(
            preferenciasSesion.getString(
                CLAVE_CONTROL,
                ""
            ) ?: ""
        )
    }

    // ==================================================
    // OTRAS VARIABLES
    // ==================================================

    var pantallaAnterior by remember {

        mutableStateOf(
            "home"
        )
    }

    var categoriaSeleccionada by remember {

        mutableStateOf("")
    }

    var politicaSeleccionada by remember {

        mutableStateOf<Policy?>(null)
    }

    // ==================================================
    // FAVORITOS
    // ==================================================

    val favoritos =
        remember {

            mutableStateListOf<Policy>()
        }

    // ==================================================
    // CARGAR FAVORITOS
    // ==================================================

    LaunchedEffect(Unit) {

        val idsGuardados =
            cargarFavoritos(
                context
            )

        val todasLasPoliticas =
            obtenerTodasLasPoliticas()

        val favoritosGuardados =
            todasLasPoliticas.filter {

                it.id in idsGuardados
            }

        favoritos.clear()

        favoritos.addAll(
            favoritosGuardados
        )
    }

    // ==================================================
    // NAVEGACIÓN
    // ==================================================

    when (pantalla) {

        // ==============================================
        // LOGIN
        // ==============================================

        "login" -> {

            LoginScreen(

                onLoginSuccess = {
                        control,
                        password ->

                    numeroControl =
                        control

                    preferenciasSesion
                        .edit()
                        .putBoolean(
                            CLAVE_SESION,
                            true
                        )
                        .putString(
                            CLAVE_CONTROL,
                            control
                        )
                        .apply()

                    pantalla =
                        "home"
                }
            )
        }

        // ==============================================
        // INICIO
        // ==============================================

        "home" -> {

            HomeScreen(

                onCategoriesClick = {

                    pantalla =
                        "categories"
                },

                onSearchClick = {

                    pantalla =
                        "search"
                },

                onFavoritesClick = {

                    pantalla =
                        "favorites"
                },

                onProfileClick = {

                    pantalla =
                        "perfil"
                }
            )
        }

        // ==============================================
        // PERFIL
        // ==============================================

        "perfil" -> {

            ProfileScreen(

                numeroControl =
                    numeroControl,

                onBack = {

                    pantalla =
                        "home"
                },

                onLogout = {

                    preferenciasSesion
                        .edit()
                        .putBoolean(
                            CLAVE_SESION,
                            false
                        )
                        .remove(
                            CLAVE_CONTROL
                        )
                        .apply()

                    numeroControl =
                        ""

                    pantalla =
                        "login"
                }
            )
        }

        // ==============================================
        // CATEGORÍAS
        // ==============================================

        "categories" -> {

            CategoriesScreen(

                onBack = {

                    pantalla =
                        "home"
                },

                onCategoryClick = {
                        categoria ->

                    categoriaSeleccionada =
                        categoria

                    pantalla =
                        "policies"
                }
            )
        }

        // ==============================================
        // POLÍTICAS
        // ==============================================

        "policies" -> {

            PoliciesScreen(

                categoria =
                    categoriaSeleccionada,

                onBack = {

                    pantalla =
                        "categories"
                },

                onPolicyClick = {
                        politica ->

                    politicaSeleccionada =
                        politica

                    pantallaAnterior =
                        "policies"

                    pantalla =
                        "detail"
                }
            )
        }

        // ==============================================
        // BÚSQUEDA
        // ==============================================

        "search" -> {

            SearchScreen(

                onBack = {

                    pantalla =
                        "home"
                },

                onPolicyClick = {
                        politica ->

                    politicaSeleccionada =
                        politica

                    pantallaAnterior =
                        "search"

                    pantalla =
                        "detail"
                },

                onHomeClick = {

                    pantalla =
                        "home"
                },

                onFavoritesClick = {

                    pantalla =
                        "favorites"
                }
            )
        }

        // ==============================================
        // FAVORITOS
        // ==============================================

        "favorites" -> {

            FavoritesScreen(

                favoritos =
                    favoritos,

                onBack = {

                    pantalla =
                        "home"
                },

                onPolicyClick = {
                        politica ->

                    politicaSeleccionada =
                        politica

                    pantallaAnterior =
                        "favorites"

                    pantalla =
                        "detail"
                },

                onRemoveFavorite = {
                        politica ->

                    favoritos.remove(
                        politica
                    )

                    guardarFavoritos(
                        context,
                        favoritos
                    )
                },

                onHomeClick = {

                    pantalla =
                        "home"
                },

                onSearchClick = {

                    pantalla =
                        "search"
                }
            )
        }

        // ==============================================
        // DETALLE
        // ==============================================

        "detail" -> {

            politicaSeleccionada?.let {
                    politica ->

                PolicyDetailScreen(

                    policy =
                        politica,

                    isFavorite =
                        favoritos.contains(
                            politica
                        ),

                    onBack = {

                        pantalla =
                            pantallaAnterior
                    },

                    onFavoriteClick = {

                        if (
                            favoritos.contains(
                                politica
                            )
                        ) {

                            favoritos.remove(
                                politica
                            )

                        } else {

                            favoritos.add(
                                politica
                            )
                        }

                        guardarFavoritos(
                            context,
                            favoritos
                        )
                    }
                )
            }
        }
    }
}