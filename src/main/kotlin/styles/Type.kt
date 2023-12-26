package styles

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val RoveTypography= Typography(
    h1 = TextStyle(

        fontSize = 50.sp,
        color = Color(0xff13AEAE)

    ),
    h2 = TextStyle(

        fontSize = 35.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xffFFFFFF)
    ),
    h3 = TextStyle(

        fontSize = 20.sp
    ),
    h6 = TextStyle(
        fontSize = 17.sp,
        color = Color(0xff737373)
    ),
    body1 = TextStyle(

        fontSize = 13.sp,
        color = Color(0xffffffff)
    ),
    body2 = TextStyle(
        fontSize = 13.sp,
        color = Color(0xff737373)
    ),
    caption = TextStyle(

        fontSize = 18.sp
    )

)