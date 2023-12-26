package components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import styles.Colors
import styles.RoveTypography

@Composable
fun button(width: Float,onClick:()->Unit, text: String){
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(width)
            .height(40.dp)
            .padding(bottom = 5.dp)
        ,
        shape = RoundedCornerShape(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors().buttonColor
        ),
        onClick = onClick
    ){
        Text(
            text = text,
            style = RoveTypography.body1
        )
    }

}