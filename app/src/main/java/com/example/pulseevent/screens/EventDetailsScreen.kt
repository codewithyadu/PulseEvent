package com.example.pulseevent.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pulseevent.model.Item
import com.example.pulseevent.ui.theme.PULSE_PRIMARY
import com.example.pulseevent.ui.theme.PULSE_PRIMARY_LIGHT
import com.example.pulseevent.ui.theme.PulseWeight
import com.example.pulseevent.ui.theme.pulseFontStyle

@Composable
fun EventDetailsScreen(item: Item) {
    LazyColumn {
        item {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberImagePainter(item.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 230.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row {
                            Text(
                                text = item.title, style = pulseFontStyle(
                                    weight = PulseWeight.Bold,
                                    fontSize = 28.sp
                                )
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .size(32.dp),
                                tint = PULSE_PRIMARY,
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null
                            )
                        }

                        Row(modifier = Modifier.padding(top = 16.dp)) {
                            Icon(
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .size(20.dp),
                                tint = PULSE_PRIMARY,
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = null
                            )
                            Column(modifier = Modifier.padding(start = 10.dp)) {
                                Text(
                                    text = item.details.date, style = pulseFontStyle(
                                        weight = PulseWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Text(
                                    text = item.time, style = pulseFontStyle(
                                        weight = PulseWeight.Normal,
                                        fontSize = 14.sp,
                                        color = PULSE_PRIMARY_LIGHT
                                    )
                                )
                            }
                        }

                        Row(modifier = Modifier.padding(top = 16.dp)) {
                            Icon(
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .size(20.dp),
                                tint = PULSE_PRIMARY,
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = null
                            )
                            Column(modifier = Modifier.padding(start = 10.dp)) {
                                Text(
                                    text = item.details.venue, style = pulseFontStyle(
                                        weight = PulseWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Text(
                                    text = item.details.place, style = pulseFontStyle(
                                        weight = PulseWeight.Normal,
                                        fontSize = 14.sp,
                                        color = PULSE_PRIMARY_LIGHT
                                    )
                                )
                            }
                        }

                        Row(modifier = Modifier.padding(top = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .size(20.dp),
                                tint = PULSE_PRIMARY,
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null
                            )
                            Column(modifier = Modifier.padding(start = 10.dp)) {
                                Text(
                                    text = item.details.price, style = pulseFontStyle(
                                        weight = PulseWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                            }
                        }

                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = "About", style = pulseFontStyle(
                                weight = PulseWeight.Bold,
                                fontSize = 18.sp
                            )
                        )

                        Text(
                            text = item.details.about, style = pulseFontStyle(
                                weight = PulseWeight.Normal,
                                fontSize = 14.sp,
                                color = PULSE_PRIMARY_LIGHT
                            )
                        )

                        Row(modifier = Modifier.padding(top = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                tint = PULSE_PRIMARY,
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null
                            )
                            Column(modifier = Modifier.padding(start = 10.dp)) {
                                Text(
                                    text = item.details.organizer, style = pulseFontStyle(
                                        weight = PulseWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
                                Text(
                                    text = "Organizer", style = pulseFontStyle(
                                        weight = PulseWeight.Normal,
                                        fontSize = 14.sp,
                                        color = PULSE_PRIMARY_LIGHT
                                    )
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            shape = RoundedCornerShape(26.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = PULSE_PRIMARY)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                textAlign = TextAlign.Center,
                                text = "BUY TICKETS @ ${item.details.price}",
                                style = pulseFontStyle(
                                    weight = PulseWeight.Normal,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    includeFontPadding = false
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}