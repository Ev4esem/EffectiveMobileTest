package com.dagteam.main_impl.impl

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dagteam.domain.models.Course
import com.dagteam.main_impl.impl.composable.CourseList
import com.dagteam.main_impl.impl.composable.ErrorScreen
import com.dagteam.main_impl.impl.composable.Filter
import com.dagteam.main_impl.impl.composable.LoadingScreen
import com.dagteam.main_impl.impl.composable.Search
import com.dagteam.main_impl.impl.mvi.MainIntent
import com.dagteam.main_impl.impl.mvi.MainUiState

@Composable
fun MainScreen(
    uiState: MainUiState,
    onIntent: (MainIntent) -> Unit,
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
        ) {
            Search()

            Filter(
                modifier = Modifier.align(Alignment.End),
                currentSortedType = uiState.currentSortedType,
                onIntent = onIntent,
            )

            CourseList(
                courses = uiState.courses,
                onIntent = onIntent,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen(
        uiState = getMockMainUiState()
    ) { }
}

private fun getMockMainUiState(): MainUiState {
    return MainUiState(
        courses = getMockCourses(),
        loading = false,
        error = null
    )
}

private fun getMockCourses(): List<Course> {
    return listOf(
        Course(
            hasLike = true,
            id = 1,
            price = "4999 ₽",
            publishDate = "22 Май 2024",
            rate = "4.8",
            startDate = "10 Июнь 2024",
            text = "В этот курс входят все необходимые материалы для начинающих разработчиков на Kotlin.",
            title = "Курс по Kotlin для начинающих"
        ),
        Course(
            hasLike = false,
            id = 2,
            price = "7499 ₽",
            publishDate = "18 Май 2024",
            rate = "4.7",
            startDate = "15 Июль 2024",
            text = "Этот курс подойдет для людей с базовыми знаниями программирования и желающих изучить Python на практике.",
            title = "Python: от новичка до профессионала"
        ),
        Course(
            hasLike = true,
            id = 3,
            price = "2999 ₽",
            publishDate = "10 Апрель 2024",
            rate = "5.0",
            startDate = "1 Август 2024",
            text = "Мастер-класс по созданию веб-приложений с использованием React и Node.js. Подходит для всех уровней.",
            title = "Веб-разработка с React и Node.js"
        ),
        Course(
            hasLike = false,
            id = 4,
            price = "3999 ₽",
            publishDate = "5 Март 2024",
            rate = "4.6",
            startDate = "20 Сентябрь 2024",
            text = "Этот курс поможет освоить основы 3D-моделирования с помощью Blender и научит работать с анимацией.",
            title = "3D-моделирование для начинающих"
        )
    )
}