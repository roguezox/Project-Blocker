package ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.restrictDialogbox.restrictDialog
import data.restrict.restrictModel
import styles.Colors
import styles.RoveTypography
import java.awt.Toolkit

@Composable

fun restrict(
    viewModel: restrictModel
) {
    val tostate = remember { mutableStateOf(false) }
    val fromstate=remember { mutableStateOf(false) }
    val configuration = Toolkit.getDefaultToolkit().screenSize
    val width = configuration.width
    val height = configuration.height

    if (tostate.value) {
        restrictDialog().restrictToDialog(
            onclose = { tostate.value = false }
        )
    }
    if (fromstate.value) {
        restrictDialog().restrictFromDialog(
            onclose = { fromstate.value = false }
        )
    }

    MaterialTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Spacer(modifier = Modifier.size((height * 0.018).dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "Restrict Usage",
                    style = RoveTypography.h2

                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.525f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.background(
                            color = Color(0xff212121),
                            shape = RoundedCornerShape(40.dp)
                        ).height((height * 0.05).dp).width((width * 0.1).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                modifier = Modifier.width((width * 0.025).dp).fillMaxHeight(),
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.Transparent
                                ),
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 0.dp,
                                    pressedElevation = 0.dp,
                                    focusedElevation = 0.dp
                                ),
                                contentPadding = PaddingValues(0.dp),
                                shape = RoundedCornerShape(60.dp)

                            ) {
                                Canvas(modifier = Modifier.size(32.dp)) {
                                    val strokeWidth = 4f
                                    val color = Color.White
                                    val canvasWidth = size.width * 0.7
                                    val canvasHeight = size.height
                                    val offset = (canvasHeight - strokeWidth) / 2
                                    drawLine(
                                        start = Offset(10f, offset),
                                        end = Offset(canvasWidth.toFloat(), offset),
                                        color = color,
                                        strokeWidth = strokeWidth
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier.background(
                                    color = Color(0xff3B3B3B),
                                    shape = RoundedCornerShape(40.dp)
                                ).height((height * 0.04).dp).width((width * 0.05).dp)
                                    .shadow(elevation = 1.dp, RoundedCornerShape(30.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    modifier = Modifier.fillMaxSize(),
                                    shape = RoundedCornerShape(60.dp),
                                    onClick = { fromstate.value = true },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Colors().grey,
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Text(
                                        ""
                                    )
                                }
                            }
                            IconButton(
                                modifier = Modifier.width((width * 0.025).dp),
                                onClick = {},
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        "From",
                        style = RoveTypography.h6
                    )
                }
                Spacer(modifier = Modifier.size((width * 0.02).dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.background(
                            color = Color(0xff212121),
                            shape = RoundedCornerShape(40.dp)
                        ).height((height * 0.05).dp).width((width * 0.1).dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                modifier = Modifier.width((width * 0.025).dp).fillMaxHeight(),
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.Transparent
                                ),
                                elevation = ButtonDefaults.elevation(
                                    defaultElevation = 0.dp,
                                    pressedElevation = 0.dp,
                                    focusedElevation = 0.dp
                                ),
                                contentPadding = PaddingValues(0.dp),
                                shape = RoundedCornerShape(60.dp)

                            ) {
                                Canvas(modifier = Modifier.size(32.dp)) {
                                    val strokeWidth = 4f
                                    val color = Color.White
                                    val canvasWidth = size.width * 0.7
                                    val canvasHeight = size.height
                                    val offset = (canvasHeight - strokeWidth) / 2
                                    drawLine(
                                        start = Offset(10f, offset),
                                        end = Offset(canvasWidth.toFloat(), offset),
                                        color = color,
                                        strokeWidth = strokeWidth
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier.height((height * 0.04).dp).width((width * 0.05).dp)
                                    .shadow(elevation = 1.dp, RoundedCornerShape(30.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    modifier = Modifier.fillMaxSize(),
                                    shape = RoundedCornerShape(60.dp),
                                    onClick = { tostate.value = true },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Colors().grey,
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Text(
                                        "00:00"
                                    )
                                }
                            }
                            IconButton(
                                modifier = Modifier.width((width * 0.025).dp),
                                onClick = {},
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        "To",
                        style = RoveTypography.h6
                    )
                }


            }
            Spacer(modifier = Modifier.size(width = width.dp, height = (height * 0.09).dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(0.23f)
                    .height(45.dp)
                    .padding(bottom = 5.dp),
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Colors().buttonColor
                ),
                onClick = {}
            ) {
                Text(
                    text = "Restrict",
                    style = RoveTypography.body1
                )
            }

        }
    }

}