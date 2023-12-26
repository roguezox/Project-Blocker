
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main()= application{
    Window(
        onCloseRequest = ::exitApplication,
        title = "test",
    ){
        Dialog(
            onCloseRequest = {}
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.background(color = Color(0xff212121)).fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        IconButton(
                            modifier = Modifier,
                            onClick = {}
                        ){
                            Icon(
                                modifier = Modifier.size(50.dp),
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = null,
                                tint = Color(0xff3B3B3B)
                            )
                        }
                        Divider(
                            modifier = Modifier.width(80.dp),
                            color = Color(0xff3B3B3B),
                            thickness = 4.dp
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        OutlinedTextField(
                            modifier = Modifier.requiredWidth(75.dp).align(
                                alignment = Alignment.CenterHorizontally
                            ),
                            singleLine = true,
                            value = "",
                            onValueChange = {},
                            placeholder = {
                                Text("00",
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 35.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = Color(0xffffffff)
                                    )
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xff2B98D5),
                                unfocusedIndicatorColor = Color.Transparent
                            )

                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Divider(
                            modifier = Modifier.width(80.dp),
                            color = Color(0xff3B3B3B),
                            thickness = 4.dp
                        )
                        IconButton(
                            modifier = Modifier,
                            onClick = {}
                        ){
                            Icon(
                                modifier = Modifier.size(50.dp),
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color(0xff3B3B3B)
                            )
                        }



                    }
                    Spacer(modifier = Modifier.size(80.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        IconButton(
                            modifier = Modifier,
                            onClick = {}
                        ){
                            Icon(
                                modifier = Modifier.size(50.dp),
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = null,
                                tint = Color(0xff3B3B3B)
                            )
                        }
                        Divider(
                            modifier = Modifier.width(80.dp),
                            color = Color(0xff3B3B3B),
                            thickness = 4.dp
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        OutlinedTextField(
                            modifier = Modifier.requiredWidth(75.dp).align(
                                alignment = Alignment.CenterHorizontally
                            ),
                            singleLine = true,
                            value = "",
                            onValueChange = {},
                            placeholder = {
                                Text("00",
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 35.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = Color(0xffffffff)
                                    )
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xff2B98D5),
                                unfocusedIndicatorColor = Color.Transparent
                            )

                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Divider(
                            modifier = Modifier.width(80.dp),
                            color = Color(0xff3B3B3B),
                            thickness = 4.dp
                        )
                        IconButton(
                            modifier = Modifier,
                            onClick = {}
                        ){
                            Icon(
                                modifier = Modifier.size(50.dp),
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = Color(0xff3B3B3B)
                            )
                        }



                    }
                }


            }
            Spacer(modifier = Modifier.size(30.dp))

        }
    }
}

