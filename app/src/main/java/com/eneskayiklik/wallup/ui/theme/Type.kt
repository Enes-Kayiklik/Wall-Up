package com.eneskayiklik.wallup.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eneskayiklik.wallup.R

private val RobotoBold = FontFamily(Font(R.font.roboto_bold))
private val RobotoLight = FontFamily(Font(R.font.robot_light))
private val RobotoThin = FontFamily(Font(R.font.roboto_thin))
private val RobotoMedium = FontFamily(Font(R.font.roboto_medium))
private val RobotoRegular = FontFamily(Font(R.font.roboto_regular))

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = RobotoBold,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = RobotoBold,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = RobotoBold,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    h6 = TextStyle(
        fontFamily = RobotoBold,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = RobotoMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = RobotoLight,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    caption = TextStyle(
        fontFamily = RobotoRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)