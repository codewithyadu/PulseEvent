package com.example.pulseevent.screens.homepage

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulseevent.R
import com.example.pulseevent.ui.theme.PULSE_PRIMARY
import com.example.pulseevent.ui.theme.PULSE_PRIMARY_LIGHT
import com.example.pulseevent.ui.theme.PulseWeight
import com.example.pulseevent.ui.theme.pulseFontStyle
import kotlinx.coroutines.delay
import java.lang.Thread.yield
import kotlin.math.absoluteValue

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .padding(top = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SearchAndProfile()
        PulseCategory()
        TrendingEventsHorizontalPager()
        UpcomingEvent()
    }
}

@Composable
fun SearchAndProfile() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = PULSE_PRIMARY,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Search events...",
            modifier = Modifier.weight(1F),
            fontWeight = FontWeight.SemiBold,
            style = pulseFontStyle(
                weight = PulseWeight.SemiBold,
                fontSize = 16.sp,
                color = PULSE_PRIMARY_LIGHT
            )
        )
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Search",
            tint = PULSE_PRIMARY,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun PulseCategory() {
    LazyRow {
        item {
            PulseCategoryChip(icon = Icons.Filled.Home, text = "All", isSelected = true)
        }
        item {
            PulseCategoryChip(icon = Icons.Filled.Settings, text = "Technology")
        }
        item {
            PulseCategoryChip(icon = Icons.Filled.AddCircle, text = "Music")
        }
        item {
            PulseCategoryChip(icon = Icons.Filled.AccountBox, text = "Fashion")
        }
        item {
            PulseCategoryChip(icon = Icons.Filled.Build, text = "Sports")
        }
        item {
            PulseCategoryChip(icon = Icons.Filled.CheckCircle, text = "Drama")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseCategoryChip(icon: ImageVector, text: String, isSelected: Boolean = false) {
    FilterChip(
        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = PULSE_PRIMARY),
        shape = RoundedCornerShape(40.dp),
        modifier = Modifier
            .padding(start = 12.dp),
        onClick = { },
        label = {
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, end = 4.dp),
                text = text,
                style = pulseFontStyle(
                    weight = PulseWeight.Normal,
                    fontSize = 18.sp,
                    color = if (isSelected) Color.White else PULSE_PRIMARY
                )
            )
        },
        selected = isSelected,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "chip icon",
                modifier = Modifier.size(24.dp),
                tint = if (isSelected) Color.White else PULSE_PRIMARY
            )
        },
    )
}

@Composable
fun UpcomingEvent() {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        PulseHeader(text = "Upcoming Event")
        LazyRow {
            item {
                UpcomingEventsCard()
            }
            item {
                UpcomingEventsCard()
            }
            item {
                UpcomingEventsCard()
            }
            item {
                UpcomingEventsCard()
            }
            item {
                UpcomingEventsCard()
            }
            item {
                UpcomingEventsCard()
            }
        }
    }
}

@Composable
fun UpcomingEventsCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
    ) {
        Box {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.poster),
                    contentDescription = null,
                    modifier = Modifier
                        .width(270.dp)
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 10.dp)) {
                    Text(
                        text = "Autumn Muse", style = pulseFontStyle(
                            weight = PulseWeight.Bold,
                            fontSize = 28.sp
                        )
                    )
                    Text(
                        text = "Music Carnival",
                        style = pulseFontStyle(
                            weight = PulseWeight.Normal,
                            fontSize = 18.sp,
                            color = PULSE_PRIMARY_LIGHT
                        )
                    )
                    Text(
                        text = "07:00 PM - 08:30 PM", style = pulseFontStyle(
                            weight = PulseWeight.Normal,
                            fontSize = 18.sp,
                            color = PULSE_PRIMARY_LIGHT
                        )
                    )
                }
            }
            Card(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 10.dp, start = 15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Text(
                    text = "08",
                    style = pulseFontStyle(
                        weight = PulseWeight.Bold,
                        color = PULSE_PRIMARY,
                        fontSize = 24.sp,
                        includeFontPadding = false
                    ),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 4.dp)
                )
                Text(
                    text = "Nov",
                    style = pulseFontStyle(
                        weight = PulseWeight.Normal,
                        fontSize = 16.sp,
                        includeFontPadding = false
                    ),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 4.dp)
                )
            }
        }
    }
}

data class Events(
    val image: Int,
    val title: String,
    val subtitle: String,
    val time: String,
    val date: String,
    val month: String
)

val trendingEventList = listOf(
    Events(
        image = R.drawable.poster,
        title = "Autumn Muse",
        subtitle = "Music Carnival",
        time = "07:00 PM - 8:30 PM",
        date = "08",
        month = "Nov."
    ),
    Events(
        image = R.drawable.arijit,
        title = "Autumn Muse",
        subtitle = "Music Carnival",
        time = "07:00 PM - 8:30 PM",
        date = "08",
        month = "Nov."
    ),
    Events(
        image = R.drawable.poster,
        title = "Autumn Muse",
        subtitle = "Music Carnival",
        time = "07:00 PM - 8:30 PM",
        date = "08",
        month = "Nov."
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingEventsHorizontalPager() {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        PulseHeader(text = "Trending Events")
        val pagerState = rememberPagerState()

        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(3000)
                pagerState.animateScrollToPage(
                    animationSpec = tween(durationMillis = 3000),
                    page = (pagerState.currentPage + 1) % (trendingEventList.size)
                )
            }
        }

        HorizontalPager(
            pageCount = trendingEventList.size, state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            TrendingEventsCard(events = trendingEventList[it], modifier = Modifier.pagerFadeTransition(
                page = it, pagerState = pagerState
            ))
        }
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(trendingEventList.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) PULSE_PRIMARY else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.pagerFadeTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset = pagerState.calculateCurrentOffsetForPage(page)
        translationX = pageOffset * size.width
        alpha = 1 - pageOffset.absoluteValue
    }

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}


@Composable
fun TrendingEventsCard(events: Events, modifier: Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
    ) {
        Box {
            Column {
                Image(
                    painter = painterResource(id = events.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 10.dp)) {
                    Text(
                        text = "Autumn Muse", style = pulseFontStyle(
                            weight = PulseWeight.Bold,
                            fontSize = 28.sp
                        )
                    )
                    Text(
                        text = "Music Carnival",
                        style = pulseFontStyle(
                            weight = PulseWeight.Normal,
                            fontSize = 18.sp,
                            color = PULSE_PRIMARY_LIGHT
                        )
                    )
                    Text(
                        text = "07:00 PM - 08:30 PM", style = pulseFontStyle(
                            weight = PulseWeight.Normal,
                            fontSize = 18.sp,
                            color = PULSE_PRIMARY_LIGHT
                        )
                    )
                }
            }
            Card(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 80.dp, start = 15.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Text(
                    text = "08",
                    style = pulseFontStyle(
                        weight = PulseWeight.Bold,
                        color = PULSE_PRIMARY,
                        fontSize = 24.sp,
                        includeFontPadding = false
                    ),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 4.dp)
                )
                Text(
                    text = "Nov",
                    style = pulseFontStyle(
                        weight = PulseWeight.Normal,
                        fontSize = 16.sp,
                        includeFontPadding = false
                    ),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 4.dp)
                )
            }
        }
    }
}

@Composable
fun PulseHeader(text: String) {
    Text(
        text = text, style = pulseFontStyle(
            weight = PulseWeight.Bold,
            fontSize = 24.sp
        ), modifier = Modifier.padding(start = 12.dp)
    )
}

@Composable
@Preview
fun SearchAndProfilePreview() {
    Surface(color = Color.White) {
        HomeScreen()
    }
}
