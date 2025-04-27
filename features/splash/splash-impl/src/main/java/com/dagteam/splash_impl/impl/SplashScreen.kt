package com.dagteam.splash_impl.impl

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dagteam.splash_impl.impl.mvi.SplashIntent
import com.dagteam.resources.R as Resource

@Composable
fun SplashScreen(
    onIntent: (SplashIntent) -> Unit,
) {
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(
                    top = 100.dp,
                    bottom = 32.dp
                ),
                text = stringResource(Resource.string.title_splash_screen),
                fontSize = 28.sp,
                color = Color(0xFFF2F2F3)
            )
            Box(
                modifier = Modifier
                    .horizontalScroll(scrollState),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.width(screenWidth.dp + 200.dp),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(Resource.drawable.courses),
                    contentDescription = null,
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF12B956)
            ),
            onClick = {
                onIntent(SplashIntent.PressedContinue)
            },
            content = {
                Text(
                    text = stringResource(Resource.string.continue_button)
                )
            }
        )
    }


}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen {

    }
}