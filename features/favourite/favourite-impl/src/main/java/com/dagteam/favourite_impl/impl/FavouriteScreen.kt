package com.dagteam.favourite_impl.impl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dagteam.domain.models.Course
import com.dagteam.favourite_impl.impl.mvi.FavouriteIntent
import com.dagteam.favourite_impl.impl.mvi.FavouriteUiState
import com.dagteam.presentation.composable.CourseItem
import com.dagteam.presentation.composable.ErrorScreen
import com.dagteam.presentation.composable.LoadingScreen
import com.dagteam.resources.R as Resources

@Composable
fun FavouriteScreen(
    uiState: FavouriteUiState,
    onIntent: (FavouriteIntent) -> Unit,
) {
    if (uiState.loading) {
        LoadingScreen()
    } else if (uiState.error != null) {
        ErrorScreen(uiState.error)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(Resources.string.title_favourite_screen),
                fontSize = 26.sp,
            )
            FavouriteCourseList(
                courses = uiState.courses,
                onIntent = onIntent,
            )
        }
    }
}

@Composable
fun FavouriteCourseList(
    courses: List<Course>,
    onIntent: (FavouriteIntent) -> Unit,
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
                onClickFavourite = {
                    onIntent(FavouriteIntent.ChangeFavouriteStatus(it.id))
                },
            )
        }
    }
}