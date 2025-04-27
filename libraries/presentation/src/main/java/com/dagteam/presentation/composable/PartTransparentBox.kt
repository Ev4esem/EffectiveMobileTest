package com.dagteam.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PartTransparentBox(
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(
                Color(0xFF32333A).copy(alpha = 0.4F),
                shape = CircleShape,
            )
            .padding(
                vertical = 4.dp,
                horizontal = 6.dp
            ),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}