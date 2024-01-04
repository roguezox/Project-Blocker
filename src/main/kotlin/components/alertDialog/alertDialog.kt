package components.alertDialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import styles.Colors
import styles.RoveTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun alertDialog(
    title:String,
    text:String,
    onDismiss: () -> Unit) {
    // Your dialog content
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        modifier = Modifier,
        enter = fadeIn(initialAlpha = 0f),
        exit = fadeOut(),
    ) {
        AlertDialog(
            backgroundColor = Color(0xff212121),
            onDismissRequest = onDismiss,
            title = { Text(text = title,
                style = TextStyle(
                    color = Color.White
                )
            ) },
            shape = RoundedCornerShape(20.dp),
            text = { Text(text,style = TextStyle(
                color = Color.White
            )) },
            confirmButton = {
                Button(onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Colors().buttonColor
                    ),
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Text("Dismiss",
                        style = RoveTypography.body1)
                }
            }
        )
    }
}