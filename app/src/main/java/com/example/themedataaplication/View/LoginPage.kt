package com.example.themedataaplication.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.themedataaplication.R
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.material.shimmer


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoginPage(navController: NavHostController) {
    val visible = remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible.value = true
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB2EBF2), // Light cyan
                        Color(0xFFE1BEE7), // Light purple
                        Color(0xFFFFF9C4)  // Light yellow
                    )
                )
            )
            .padding(32.dp)
    ) {
        AnimatedVisibility(
            visible = visible.value,
            enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Logo",
                        modifier = Modifier.size(60.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "\nSmart\nLogin",
                    color = Color.White,
                    fontSize = 32.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(48.dp))

        AnimatedVisibility(
            visible = visible.value,
            enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn()
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bem-vindo de volta",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF203A43)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Acesse seu painel inteligente",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(32.dp))

                val textFieldModifier = Modifier
                    .fillMaxWidth(0.85f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.8f))
                    .focusable()

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail", color = Color(0xFF203A43)) },
                    leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color(0xFF203A43)) },
                    modifier = textFieldModifier,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF81D4FA),
                        unfocusedBorderColor = Color(0xFF4DD0E1),
                        cursorColor = Color.Black,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha", color = Color(0xFF203A43)) },
                    leadingIcon = { Icon(Icons.Default.Key, contentDescription = null, tint = Color(0xFF203A43)) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(Icons.Default.Visibility, contentDescription = null, tint = Color(0xFF203A43))
                        }
                    },
                    modifier = textFieldModifier,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFCE93D8),
                        unfocusedBorderColor = Color(0xFFBA68C8),
                        cursorColor = Color.Black,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {},
                            colors = CheckboxDefaults.colors(
                                checkmarkColor = Color.Black,
                                uncheckedColor = Color.Gray,
                                checkedColor = Color(0xFFE1BEE7)
                            )
                        )
                        Text("Lembrar", color = Color(0xFF203A43))
                    }

                    Text(
                        text = "Esqueceu a senha?",
                        color = Color(0xFF203A43)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        isLoading = true
                        navController.navigate("home")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(60.dp)
                        .placeholder(
                            visible = isLoading,
                            color = Color(0xFFB2EBF2),
                            shape = RoundedCornerShape(20.dp),
                            highlight = PlaceholderHighlight.shimmer()
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB2EBF2)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Entrar", color = Color(0xFF203A43), fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row {
                    Text("Não tem uma conta?", color = Color(0xFF203A43))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cadastre-se", color = Color(0xFF203A43))
                }
            }
        }
    }
}
