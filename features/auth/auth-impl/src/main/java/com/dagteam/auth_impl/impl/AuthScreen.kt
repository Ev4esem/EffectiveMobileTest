package com.dagteam.auth_impl.impl

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dagteam.auth_impl.impl.mvi.AuthIntent
import com.dagteam.auth_impl.impl.mvi.AuthUiState

@Composable
fun AuthScreen(
    uiState: AuthUiState,
    onIntent: (AuthIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 100.dp,
                        bottom = 28.dp,
                    ),
                textAlign = TextAlign.Left,
                text = "Вход",
                fontSize = 28.sp,
                color = Color(0xFFF2F2F3)
            )
            val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
    
            val isEmailValid = uiState.user.email.matches(emailRegex)
            val isPasswordValid = uiState.user.password.isNotEmpty()
            val isLoginButtonEnabled = isEmailValid && isPasswordValid
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextInputBasic(
                    title = "Email",
                    hint = "example@gmail.com",
                    value = uiState.user.email,
                    onChanged = {
                        onIntent(AuthIntent.ChangeEmail(it))
                    },
                    keyboardType = KeyboardType.Email,
                )
                TextInputBasic(
                    title = "Пароль",
                    hint = "Введите пароль",
                    value = uiState.user.password,
                    onChanged = {
                        onIntent(AuthIntent.ChangePassword(it))
                    },
                    keyboardType = KeyboardType.Password,
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF12B956),
                    disabledContainerColor = Color(0xFF88C2A0)
                ),
                enabled = isLoginButtonEnabled,
                onClick = {
                    onIntent(AuthIntent.PressedSignIn)
                },
                content = {
                    Text(
                        text = "Войти",
                        color = Color.White
                    )
                }
            )
            Column(
                modifier = Modifier.padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val annotatedString = buildAnnotatedString {
                    append("Нет аккаунта? ")
                    withStyle(style = SpanStyle(color = Color(0xFF12B956))) {
                        append("Регистрация")
                    }
                    append("\n")
                    withStyle(style = SpanStyle(color = Color(0xFF12B956))) {
                        append("Забыл пароль")
                    }
                }
    
                Text(
                    text = annotatedString,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 32.dp))
    
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = {
                            onIntent(AuthIntent.PressedVK)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4C75A3)),
                        modifier = Modifier.width(150.dp)
                    ) {
                        Icon(
                            painter = painterResource(com.dagteam.resources.R.drawable.ic_vk),
                            contentDescription = null
                        )
                    }
    
                    Button(
                        onClick = {
                            onIntent(AuthIntent.PressedOK)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8C00)),
                        modifier = Modifier.width(150.dp)
                    ) {
                        Icon(
                            painter = painterResource(com.dagteam.resources.R.drawable.ic_ok),
                            contentDescription = null
                        )
                    }
                }
            }
        }
}

@Composable
fun OpenSocialMedia(url: String) {
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

@Composable
fun TextInputBasic(
    title: String,
    hint: String,
    value: String,
    keyboardType: KeyboardType,
    onChanged: (String) -> Unit,
) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF2F2F3),
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onChanged,
            decorationBox = {
                Text(
                   text = hint,
                   color = Color(0xFFF2F2F3),
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType
            ),
            keyboardActions = KeyboardActions.Default,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xFF32333A))
                .padding(10.dp),
        )
    }

}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFF151515
)
fun AuthScreenPreview() {
    AuthScreen(
        uiState = AuthUiState()
    ) { }
}