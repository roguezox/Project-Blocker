package components.restrictDialogbox

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
        viewModel: restrictModel,
        from: String
    ){

        var fromH by remember{ mutableStateOf(from.split(":")[0]) }
        var fromM by remember{ mutableStateOf(from.split(":")[1]) }

        Dialog(
            enabled = true,
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
                            onClick = {
                                if(fromH.toInt()<24){
                                    fromH=(fromH.toInt()+1).toString()
                                }
                            }
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
                            onValueChange = {
                                if (it.isEmpty() || it.contains(regex = Regex("^[0-9]*\$"))) {
                                    if(it.isEmpty()||(it.toInt() in 0..24)){
                                        fromH=it

                                    } }
                                            },

                            textStyle = TextStyle(
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffffffff)
                            ),


                            placeholder = {
                                Text("Hr",
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 25.sp,
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
                            onClick = {
                                if(fromH.toInt()>0){
                                    fromH= (fromH.toInt()-1).toString()
                                }
                            }
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
                            onClick = {
                                if(fromM.toInt()<60){
                                    fromM= (fromM.toInt()+1).toString()
                                }
                            }
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
                            onValueChange = {
                                if (it.isEmpty() || it.contains(regex = Regex("^[0-9]*\$"))) {
                                    if(it.isEmpty()||(it.toInt() in 0..60)){
                                        fromM=it

                                    } }
                            },
                            textStyle = TextStyle(
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffffffff)
                            ),
                            placeholder = {
                                Text("Min",
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 25.sp,
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
                            onClick = {
                                if(fromM.toInt()>0){
                                    fromM=(fromM.toInt()-1).toString()
                                }
                            }
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
                        if(fromH!==""&&fromM!=""){
                            viewModel.savefrom(from)
                        }
                        else{
                           if(fromH==""&&fromM==""){
                               fromH="00"
                               fromM="00"
                               val from= "$fromH:$fromM"
                               viewModel.savefrom(from)
                           } else if(fromH==""){
                               fromH="00"
                               val from= "$fromH:$fromM"
                               viewModel.savefrom(from)
                           } else{
                               fromM="00"
                               val from= "$fromH:$fromM"
                               viewModel.savefrom(from)
                           }
                        }


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
    fun restrictToDialog(
        onclose: ()-> Unit,
        viewModel: restrictModel,
        to:String
    ){
        var toH by remember { mutableStateOf(to.split(":")[0]) }
        var toM by remember { mutableStateOf(to.split(":")[1]) }


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
                            onClick = {
                                if(toH.toInt()<24){
                                    toH=(toH.toInt()+1).toString()
                                }
                            }
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
                                if (it.isEmpty() || it.contains(regex = Regex("^[0-9]*\$"))) {
                                    if(it.isEmpty()||(it.toInt() in 0..24)){
                                        toH=it

                                    } }
                            },
                            textStyle = TextStyle(
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffffffff)
                            ),
                            placeholder = {
                                Text("Hr",
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 25.sp,
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
                            onClick = {
                                if(toH.toInt()>0){
                                    toH=(toH.toInt()-1).toString()
                                }
                            }
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
                            onClick = {
                                if(toM.toInt()<60){
                                    toM=(toM.toInt()+1).toString()
                                }
                            }
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
                                if (it.isEmpty() || it.contains(regex = Regex("^[0-9]*\$"))) {
                                    if(it.isEmpty()||(it.toInt() in 0..60)){
                                        toM=it

                                    } }
                            },
                            textStyle = TextStyle(
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xffffffff)
                            ),
                            placeholder = {
                                Text("Min",
                                    modifier = Modifier,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 25.sp,
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
                            onClick = {
                                if(toM.toInt()>0){
                                    toM=(toM.toInt()-1).toString()
                                }
                            }
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

                        if(toH!==""&&toM!=""){
                            val to= "$toH:$toM"
                            viewModel.saveto(to)
                        }
                        else{
                            if(toH==""&&toM==""){
                                toH="01"
                                toM="00"
                                val to= "$toH:$toM"
                                viewModel.savefrom(to)
                            } else if(toH==""){
                                toH="01"
                                val from= "$toH:$toM"
                                viewModel.savefrom(from)
                            } else{
                                toM="00"
                                val to= "$toH:$toM"
                                viewModel.savefrom(to)
                            }
                        }

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun dialog(onDismiss: () -> Unit) {
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
            modifier = Modifier.height(200.dp).width(400.dp),
            backgroundColor = Color(0xff212121),
            onDismissRequest =onDismiss,
            title = { Text("Invalid Range!") },
            shape = RoundedCornerShape(20.dp),
            text = { Text("Invalid time entered") },
            contentColor = Color(0xffffffff),
            confirmButton = {
                Button(onClick = onDismiss,
                    modifier = Modifier.height(40.dp).width(100.dp),
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



