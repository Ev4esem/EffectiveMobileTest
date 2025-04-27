package com.dagteam.main_impl.impl.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionOnScreen
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.dagteam.domain.models.SortedType
import com.dagteam.main_impl.impl.mvi.MainIntent
import com.dagteam.resources.R as Resources

@Composable
fun Filter(
    modifier: Modifier = Modifier,
    currentSortedType: SortedType,
    onIntent: (MainIntent) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var size by remember { mutableStateOf(IntSize(0, 0)) }
    TextButton(
        modifier = modifier
            .onPlaced {
                offset = it.positionOnScreen()
                size = it.size
            },
        onClick = {
            expanded = !expanded
        },
        content = {
            Text(
                text = stringResource(currentSortedType.titleRes),
                color = Color(0xFF12B956),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(Resources.drawable.ic_sorter),
                tint = Color(0xFF12B956),
                contentDescription = null
            )
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        offset = offset.toDpOffset(size),
    ) {
        SortedType.entries.forEach { sortedType ->
            DropdownMenuItem(
                modifier = Modifier.wrapContentWidth(),
                text = {
                    Text(
                        text = stringResource(sortedType.titleRes),
                        color = Color(0xFF12B956)
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(Resources.drawable.ic_sorter),
                        tint = Color(0xFF12B956),
                        contentDescription = null
                    )
                },
                onClick = {
                    onIntent(MainIntent.SelectedSorted(sortedType))
                    expanded = false
                }
            )
        }
    }
}

@Composable
private fun Offset.toDpOffset(size: IntSize): DpOffset {
    val density = LocalDensity.current.density
    return DpOffset(
        x = (this.x / density).dp,
        y = ((this.y + size.height) / density).dp
    )
}