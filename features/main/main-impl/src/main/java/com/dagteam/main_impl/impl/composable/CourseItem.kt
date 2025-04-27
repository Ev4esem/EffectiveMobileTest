package com.dagteam.main_impl.impl.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dagteam.domain.models.Course
import com.dagteam.main_impl.impl.mvi.MainIntent
import com.dagteam.resources.R as Resources

@Composable
fun CourseItem(
    course: Course,
    onIntent: (MainIntent) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(235.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF24252A)
        ),
        onClick = { /* no op */ },
        content = {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(115.dp)
                        .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp)),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(Resources.drawable.example_image),
                    contentDescription = null,
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        PartTransparentBox(
                            content = {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        painter = painterResource(Resources.drawable.ic_rate),
                                        tint = Color(0xFF12B956),
                                        contentDescription = null,
                                    )
                                    Text(
                                        text = course.rate,
                                        color = Color.White,
                                    )
                                }

                            }
                        )
                        PartTransparentBox(
                            content = {
                                Text(
                                    text = course.startDate,
                                    color = Color.White,
                                )
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd),
                ) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color(0xFF32333A).copy(alpha = 0.3F),
                        ),
                        onClick = {
                            onIntent(MainIntent.ChangeFavouriteStatus(course.id))
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                if (course.hasLike) Resources.drawable.ic_fill_favourite else Resources.drawable.ic_favourite,
                            ),
                            tint = if (course.hasLike) Color(0xFF12B956) else Color.White,
                            contentDescription = null,
                        )
                    }
                }

            }
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    fontSize = 16.sp,
                    text = course.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(
                        top = 12.dp,
                    ),
                    maxLines = 2,
                    text = course.text,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFFF2F2F3)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = course.price + stringResource(Resources.string.currency_main_screen),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = stringResource(Resources.string.description_main_screen),
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color(0xFF12B956)
                        )
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(Resources.drawable.ic_arrow),
                            tint = Color(0xFF12B956),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    )
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFF151515
)
fun CourseItemPreview() {
    CourseItem(
        course = Course(
            hasLike = false,
            id = 1,
            price = "999",
            publishDate = "22 Мая 2024",
            rate = "4.8",
            startDate = "22 Мая 2024",
            text = "Освойте backend-разработку \u2028и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
            title = "Java-разработчик с нуля",
        )
    ) { }
}