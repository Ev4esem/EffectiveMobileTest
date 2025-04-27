package com.dagteam.main_impl.impl.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dagteam.domain.models.Course
import com.dagteam.main_impl.impl.mvi.MainIntent

@Composable
fun CourseList(
    courses: List<Course>,
    onIntent: (MainIntent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
           items = courses,
           key = Course::id,
        ) {
            CourseItem(
                course = it,
                onIntent = onIntent,
            )
        }
    }
}