package com.dagteam.main_impl.impl.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dagteam.resources.R as Resources

@Composable
fun Search() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BasicTextField(
            value = "",
            onValueChange = {},
            decorationBox = {
                Row(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = Color.White,
                        contentDescription = null,
                    )
                    Text(
                        text = stringResource(Resources.string.search_hint_main_screen),
                        color = Color(0xFFF2F2F3),
                    )
                }
            },
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .clip(RoundedCornerShape(38.dp))
                .background(Color(0xFF24252A))
                .padding(12.dp),
        )
        IconButton(
            modifier = Modifier.size(56.dp),
            onClick = { /* no op */ },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xFF24252A)
            )
        ) {
            Icon(
                painter = painterResource(Resources.drawable.ic_filter),
                tint = Color.White,
                contentDescription = null,
            )
        }
    }
}