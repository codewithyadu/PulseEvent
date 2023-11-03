package com.example.pulseevent.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.pulseevent.CommonViewModel
import com.example.pulseevent.R
import com.example.pulseevent.model.Item
import com.example.pulseevent.model.Trending
import com.example.pulseevent.model.Upcoming
import com.example.pulseevent.ui.theme.PULSE_PRIMARY
import com.example.pulseevent.ui.theme.PULSE_PRIMARY_LIGHT
import com.example.pulseevent.ui.theme.PulseWeight
import com.example.pulseevent.ui.theme.pulseFontStyle
import kotlinx.coroutines.delay
import java.lang.Thread.yield
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    commonViewModel: CommonViewModel,
    navigateToDetailsScreen: (String) -> Unit
) {
    val pulseModelData = commonViewModel.pulseModelData.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .padding(top = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SearchAndProfile()
        PulseCategory()
        pulseModelData.value?.let {
            TrendingEventsHorizontalPager(it.trending, navigateToDetailsScreen)
            UpcomingEvent(it.upcoming, navigateToDetailsScreen)
        } ?: CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 20.dp))
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
fun UpcomingEvent(upcoming: Upcoming, navigateToDetailsScreen: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        PulseHeader(text = "Upcoming Event")
        LazyRow() {
            items(upcoming.items) {
                UpcomingEventsCard(it, navigateToDetailsScreen)
            }
        }
    }
}

@Composable
fun UpcomingEventsCard(item: Item, navigateToDetailsScreen: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .clickable {
                navigateToDetailsScreen(item.title)
            }
    ) {
        Box {
            Column {
                Image(
                    painter = rememberImagePainter(item.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .width(270.dp)
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 10.dp)) {
                    Text(
                        text = item.title, style = pulseFontStyle(
                            weight = PulseWeight.Bold,
                            fontSize = 28.sp
                        )
                    )
                    Text(
                        text = item.subtitle,
                        style = pulseFontStyle(
                            weight = PulseWeight.Normal,
                            fontSize = 18.sp,
                            color = PULSE_PRIMARY_LIGHT
                        )
                    )
                    Text(
                        text = item.time, style = pulseFontStyle(
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
                    text = item.date,
                    style = pulseFontStyle(
                        weight = PulseWeight.Bold,
                        color = PULSE_PRIMARY,
                        fontSize = 24.sp,
                        includeFontPadding = false
                    ),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 4.dp)
                )
                Text(
                    text = item.month,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingEventsHorizontalPager(trending: Trending, navigateToDetailsScreen: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        PulseHeader(text = trending.title)
        val pagerState = rememberPagerState()

        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(3000)
                pagerState.animateScrollToPage(
                    animationSpec = tween(durationMillis = 3000),
                    page = (pagerState.currentPage + 1) % (trending.items.size)
                )
            }
        }

        HorizontalPager(
            pageCount = trending.items.size, state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            TrendingEventsCard(item = trending.items[it], modifier = Modifier.pagerFadeTransition(
                page = it, pagerState = pagerState
            ), navigateToDetailsScreen)
        }
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(trending.items.size) { iteration ->
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
fun TrendingEventsCard(item: Item, modifier: Modifier, navigateToDetailsScreen: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier.then(Modifier.clickable {
            navigateToDetailsScreen(item.title)
        })
    ) {
        Box {
            Column {
                Image(
                    painter = rememberImagePainter(item.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 15.dp, top = 28.dp, bottom = 10.dp)) {
                    Text(
                        text = item.title, style = pulseFontStyle(
                            weight = PulseWeight.Bold,
                            fontSize = 28.sp
                        )
                    )
                    Text(
                        text = item.subtitle,
                        style = pulseFontStyle(
                            weight = PulseWeight.Normal,
                            fontSize = 18.sp,
                            color = PULSE_PRIMARY_LIGHT
                        )
                    )
                    Text(
                        text = item.time, style = pulseFontStyle(
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
                    text = item.date,
                    style = pulseFontStyle(
                        weight = PulseWeight.Bold,
                        color = PULSE_PRIMARY,
                        fontSize = 24.sp,
                        includeFontPadding = false
                    ),
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 4.dp)
                )
                Text(
                    text = item.month,
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
