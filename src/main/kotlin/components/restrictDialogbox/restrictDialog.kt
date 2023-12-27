package components.restrictDialogbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import data.restrict.restrictModel
import styles.Colors
import styles.RoveTypography

class restrictDialog{

    @Composable
    fun restrictFromDialog(
        onclose: () -> Unit,
        viewModel: restrictModel
    ){
        var fromH by remember{ mutableStateOf("") }
        var fromM by remember{ mutableStateOf("") }


        Dialog(
            onCloseRequest = onclose,
            title = "From"
        ){

            Column(
                modifier = Modifier.fillMaxSize().background(color = Colors().primaryDarker),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.background(color = Colors().primaryDarker).fillMaxWidth().fillMaxHeight(0.8f),
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
                            value =fromH,
                            onValueChange = {fromH=it},
                            textStyle = TextStyle(
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffffffff)
                            ),
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
                            value = fromM,
                            onValueChange = {fromM=it},
                            textStyle = TextStyle(
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffffffff)
                            ),
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
                                focusedIndicatorColor = Colors().buttonColor,
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

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(0.4f)
                        .height(40.dp)
                        .padding(bottom = 5.dp)
                    ,
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Colors().buttonColor
                    ),
                    onClick = {
                        val from= "$fromH:$fromM"
                        viewModel.savefrom(from)


                    }
                ){
                    Text(
                        text = "Save",
                        style = RoveTypography.body1
                    )
                }

            }


        }
    }

    @Composable
    fun restrictToDialog(onclose: ()-> Unit,
                         viewModel: restrictModel){
        var toH by remember{ mutableStateOf("") }
        var toM by remember{ mutableStateOf("") }

        Dialog(
            onCloseRequest = onclose,
            title = "To"
        ){
            Column(
                modifier = Modifier.fillMaxSize().background(color = Colors().primaryDarker),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.background(color = Colors().primaryDarker).fillMaxWidth().fillMaxHeight(0.8f),
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
                            value = toH,
                            onValueChange = {
                                            toH=it
                            },
                            textStyle = TextStyle(
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffffffff)
                            ),
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
                            value = toM,
                            onValueChange = {
                                toM=it

                            },
                            textStyle = TextStyle(
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffffffff)
                            ),
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
                                focusedIndicatorColor = Colors().buttonColor,
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

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(0.4f)
                        .height(40.dp)
                        .padding(bottom = 5.dp)
                    ,
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Colors().buttonColor
                    ),
                    onClick = {
                        val to= "$toH:$toM"
                        viewModel.saveto(to)

                    }
                ){
                    Text(
                        text = "Save",
                        style = RoveTypography.body1
                    )
                }

            }


        }
    }
}


