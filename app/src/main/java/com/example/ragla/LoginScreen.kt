package com.example.ragla

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginSuccess: (String, String) -> Unit
) {

    var numeroControl by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var mostrarPassword by remember {
        mutableStateOf(false)
    }

    var mostrarErrorControl by remember {
        mutableStateOf(false)
    }

    var mostrarErrorPassword by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF6F3F8)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 22.dp,
                    vertical = 20.dp
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            // =====================================================
            // TARJETA PRINCIPAL + LOGO
            // =====================================================

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {

                // =================================================
                // TARJETA
                // =================================================

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 38.dp),

                    shape =
                        RoundedCornerShape(28.dp),

                    colors =
                        CardDefaults.cardColors(
                            containerColor = Color.White
                        ),

                    elevation =
                        CardDefaults.cardElevation(
                            defaultElevation = 7.dp
                        )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 22.dp,
                                end = 22.dp,
                                top = 58.dp,
                                bottom = 22.dp
                            ),

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        // =============================================
                        // TÍTULO
                        // =============================================

                        Text(
                            text = "Iniciar sesión",

                            style =
                                MaterialTheme
                                    .typography
                                    .headlineSmall,

                            fontWeight =
                                FontWeight.Bold,

                            color =
                                Color(0xFF29252D)
                        )

                        Spacer(
                            modifier = Modifier.height(5.dp)
                        )

                        Text(
                            text =
                                "Accede a las políticas escolares",

                            style =
                                MaterialTheme
                                    .typography
                                    .bodySmall,

                            color =
                                Color(0xFF777277)
                        )

                        Spacer(
                            modifier = Modifier.height(22.dp)
                        )

                        // =============================================
                        // NÚMERO DE CONTROL
                        // =============================================

                        CapsuleField(
                            value = numeroControl,

                            onValueChange = { nuevoValor ->

                                if (
                                    nuevoValor.length <= 8 &&
                                    nuevoValor.all { caracter ->
                                        caracter.isDigit()
                                    }
                                ) {

                                    numeroControl =
                                        nuevoValor

                                    mostrarErrorControl =
                                        false
                                }
                            },

                            icono = "👤",

                            placeholder =
                                "Número de control",

                            keyboardType =
                                KeyboardType.Number
                        )

                        Spacer(
                            modifier = Modifier.height(5.dp)
                        )

                        Row(
                            modifier =
                                Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween
                        ) {

                            Text(
                                text =
                                    if (mostrarErrorControl) {
                                        "Debe contener 8 dígitos."
                                    } else {
                                        "Ej. 23270081"
                                    },

                                style =
                                    MaterialTheme
                                        .typography
                                        .bodySmall,

                                color =
                                    if (mostrarErrorControl) {
                                        MaterialTheme
                                            .colorScheme
                                            .error
                                    } else {
                                        Color(0xFF88838A)
                                    }
                            )

                            Text(
                                text =
                                    "${numeroControl.length}/8",

                                style =
                                    MaterialTheme
                                        .typography
                                        .bodySmall,

                                fontWeight =
                                    FontWeight.Medium,

                                color =
                                    if (
                                        numeroControl.length == 8
                                    ) {
                                        Color(0xFF388E3C)
                                    } else {
                                        Color(0xFF88838A)
                                    }
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )

                        // =============================================
                        // CONTRASEÑA
                        // =============================================

                        CapsulePasswordField(
                            value = password,

                            onValueChange = { nuevoPassword ->

                                password =
                                    nuevoPassword

                                mostrarErrorPassword =
                                    false
                            },

                            mostrarPassword =
                                mostrarPassword,

                            onMostrarPassword = {

                                mostrarPassword =
                                    !mostrarPassword
                            }
                        )

                        if (mostrarErrorPassword) {

                            Text(
                                text =
                                    "Ingresa tu contraseña.",

                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 12.dp,
                                            top = 5.dp
                                        ),

                                style =
                                    MaterialTheme
                                        .typography
                                        .bodySmall,

                                color =
                                    MaterialTheme
                                        .colorScheme
                                        .error
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(22.dp)
                        )

                        // =============================================
                        // BOTÓN
                        // =============================================

                        Button(
                            onClick = {

                                val controlValido =
                                    numeroControl.length == 8

                                val passwordValido =
                                    password.isNotBlank()

                                mostrarErrorControl =
                                    !controlValido

                                mostrarErrorPassword =
                                    !passwordValido

                                if (
                                    controlValido &&
                                    passwordValido
                                ) {

                                    onLoginSuccess(
                                        numeroControl,
                                        password
                                    )
                                }
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),

                            shape =
                                RoundedCornerShape(50.dp)
                        ) {

                            Text(
                                text =
                                    "INICIAR SESIÓN",

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )

                        Text(
                            text =
                                "Acceso exclusivo para estudiantes",

                            style =
                                MaterialTheme
                                    .typography
                                    .bodySmall,

                            color =
                                Color(0xFF8A858B)
                        )
                    }
                }

                // =================================================
                // LOGO SOBRESALIENTE
                // =================================================

                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF4A148C),
                                    Color(0xFF7B1FA2)
                                )
                            )
                        ),

                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text = "R",

                        color =
                            Color.White,

                        style =
                            MaterialTheme
                                .typography
                                .headlineLarge,

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // =====================================================
            // NOMBRE DE LA APLICACIÓN
            // =====================================================

            Text(
                text = "RAGLA",

                style =
                    MaterialTheme
                        .typography
                        .headlineMedium,

                fontWeight =
                    FontWeight.Bold,

                color =
                    Color(0xFF4A148C)
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text =
                    "Asistente de Políticas Escolares",

                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,

                color =
                    Color(0xFF6F6A70)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text =
                    "RAGLA • 2026",

                style =
                    MaterialTheme
                        .typography
                        .labelSmall,

                color =
                    Color(0xFF999499)
            )
        }
    }
}


// =================================================================
// CAMPO TIPO CÁPSULA
// =================================================================

@Composable
fun CapsuleField(
    value: String,
    onValueChange: (String) -> Unit,
    icono: String,
    placeholder: String,
    keyboardType: KeyboardType
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(
                RoundedCornerShape(50.dp)
            )
            .background(
                Color(0xFFF0EEF2)
            )
            .padding(
                horizontal = 16.dp
            ),

        contentAlignment =
            Alignment.CenterStart
    ) {

        Row(
            modifier =
                Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = icono,

                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Box(
                modifier =
                    Modifier.weight(1f)
            ) {

                if (value.isEmpty()) {

                    Text(
                        text = placeholder,

                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium,

                        color =
                            Color(0xFF8C878E)
                    )
                }

                BasicTextField(
                    value = value,

                    onValueChange =
                        onValueChange,

                    modifier =
                        Modifier.fillMaxWidth(),

                    singleLine = true,

                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType =
                                keyboardType
                        ),

                    textStyle =
                        MaterialTheme
                            .typography
                            .bodyLarge
                            .copy(
                                color =
                                    Color(0xFF302C32)
                            )
                )
            }
        }
    }
}


// =================================================================
// CAMPO DE CONTRASEÑA TIPO CÁPSULA
// =================================================================

@Composable
fun CapsulePasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    mostrarPassword: Boolean,
    onMostrarPassword: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(
                RoundedCornerShape(50.dp)
            )
            .background(
                Color(0xFFF0EEF2)
            )
            .padding(
                horizontal = 16.dp
            ),

        contentAlignment =
            Alignment.CenterStart
    ) {

        Row(
            modifier =
                Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "🔒",

                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Box(
                modifier =
                    Modifier.weight(1f)
            ) {

                if (value.isEmpty()) {

                    Text(
                        text =
                            "Contraseña",

                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium,

                        color =
                            Color(0xFF8C878E)
                    )
                }

                BasicTextField(
                    value = value,

                    onValueChange =
                        onValueChange,

                    modifier =
                        Modifier.fillMaxWidth(),

                    singleLine = true,

                    visualTransformation =
                        if (mostrarPassword) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },

                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType =
                                KeyboardType.Password
                        ),

                    textStyle =
                        MaterialTheme
                            .typography
                            .bodyLarge
                            .copy(
                                color =
                                    Color(0xFF302C32)
                            )
                )
            }

            TextButton(
                onClick =
                    onMostrarPassword
            ) {

                Text(
                    text =
                        if (mostrarPassword) {
                            "Ocultar"
                        } else {
                            "Ver"
                        },

                    color =
                        Color(0xFF6A1B9A),

                    fontWeight =
                        FontWeight.Medium
                )
            }
        }
    }
}