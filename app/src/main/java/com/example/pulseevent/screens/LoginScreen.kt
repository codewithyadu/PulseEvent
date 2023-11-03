package com.example.pulseevent.screens

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pulseevent.CommonViewModel
import com.example.pulseevent.R
import com.example.pulseevent.ui.theme.PULSE_PRIMARY
import com.example.pulseevent.ui.theme.PulseWeight
import com.example.pulseevent.ui.theme.pulseFontStyle

@Composable
fun LoginScreen(
    navigateToHomeScreen: () -> Unit,
    commonViewModel: CommonViewModel = hiltViewModel()
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                commonViewModel.getGoogleSignedInUserResultFromIntent(result, navigateToHomeScreen)
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.concert),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 20.dp, vertical = 40.dp)
        ) {
            Text(
                text = "Explore the best events in your college",
                style = pulseFontStyle(
                    weight = PulseWeight.Bold,
                    color = Color.White,
                    fontSize = 24.sp,
                    includeFontPadding = false
                )
            )
            Text(
                modifier = Modifier.padding(top = 6.dp, bottom = 14.dp, end = 20.dp),
                text = "Discover the best events and book tickets in just a few steps.",
                style = pulseFontStyle(
                    weight = PulseWeight.Normal,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    includeFontPadding = false
                )
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        commonViewModel.signInUserViaGoogle(launcher)
                    },
                shape = RoundedCornerShape(26.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(containerColor = PULSE_PRIMARY)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center,
                    text = "Sign In With Google",
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