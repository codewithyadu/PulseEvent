package com.example.pulseevent.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.pulseevent.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

fun pulseFontStyle(
    weight: PulseWeight,
    color: Color = Color.Unspecified,
    fontSize: TextUnit,
    includeFontPadding: Boolean = true
) = TextStyle(
    fontFamily = PULSE_FONT,
    fontWeight = weight.value,
    fontSize = fontSize,
    color = color,
    platformStyle = PlatformTextStyle(
        includeFontPadding = includeFontPadding,
    )
)

enum class PulseWeight(val value: FontWeight) {
    Bold(value = FontWeight.Bold),
    SemiBold(value = FontWeight.SemiBold),
    Medium(value = FontWeight.Medium),
    Normal(value = FontWeight.Normal),
}

val PULSE_FONT = FontFamily(
    Font(R.font.firasansbold),
    Font(R.font.firasansmedium),
    Font(R.font.firasansregular),
    Font(R.font.firasanssemibold)
)
