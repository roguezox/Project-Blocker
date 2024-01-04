package ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.button.button
import data.blocker.blockerModel
import data.home.homeModel
import styles.Colors
import styles.RoveTypography
import java.awt.Toolkit
import java.util.Timer

@Composable
@Preview
fun home(
   viewModel: homeModel,
   blockerModel: blockerModel

) {
    val configuration= Toolkit.getDefaultToolkit().screenSize
    val width = configuration.width
    val height= configuration.height
    val blockerState by blockerModel.blockerState.collectAsState()

    val stateModel by viewModel.homeState.collectAsState()
    val init= "Select withKeywords from user"
    val initprep= blockerState.connection.prepareStatement(init)
    val res= initprep.executeQuery()

    var initIndex=9
    while (res.next()){
        initIndex=res.getInt("withKeywords")

        break
    }
    initprep.close()
    if(initIndex==0){
        viewModel.updateRun(0,9)
    }else if(initIndex==1){
        viewModel.updateRun(1,9)
    }

    MaterialTheme(

    ) {
        Column(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.size((height*0.018).dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = "Blocker",
                    style = RoveTypography.h2

                )
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column (
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    button(
                        width=0.23f,
                        onClick = {
                            if(stateModel.isConnected){
                                viewModel.defaultDisconnect(stateModel.withKeywords)
                            } else {
                                viewModel.defaultConnect(stateModel.withKeywords)

                            }
                        },
                        text = stateModel.button
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = stateModel.status,
                        style = RoveTypography.body2
                    )




                }
                Spacer(modifier = Modifier.fillMaxWidth(0.05f))
                Divider(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp,bottom = 20.dp)
                        .height(150.dp)
                        .width(1.dp)
                        .background(color = Color(0xff4D4D4D))
                )
                Spacer(modifier = Modifier.fillMaxWidth(0.05f))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Block the keywords",
                            style = TextStyle(
                                color = Color(0xff737373),
                                fontSize = 15.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Switch(
                            checked = stateModel.withKeywords,
                            onCheckedChange = {
                                if(it){
                                    if(stateModel.button=="Disconnect"){

                                        viewModel.defaultDisconnect(it)

                                        viewModel.keywordConnect()
                                        Thread.sleep(1000)
                                        print("state "+stateModel.withKeywords+"\n")
                                        viewModel.defaultConnect(it)
                                    }else{
                                        viewModel.keywordConnect()
                                    }


                                }else{
                                    if(stateModel.button=="Disconnect"){
                                        viewModel.defaultDisconnect(it)
                                        viewModel.keywordDisconnect()
                                        Thread.sleep(1000)
                                        viewModel.defaultConnect(it)
                                    }else{
                                        viewModel.keywordDisconnect()
                                    }

                                }
                                              },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Colors().buttonColor
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Enable time restriction",
                            style = TextStyle(
                                color = Color(0xff737373),
                                fontSize = 15.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Switch(

                            checked = stateModel.withRestrict,
                            onCheckedChange = {
                                if(it){
                                    if(stateModel.isConnected){
                                        viewModel.defaultDisconnect(stateModel.withKeywords)
                                    }
                                    viewModel.timeConnect(it)
                                    if(stateModel.isConnected){
                                        viewModel.defaultConnect(stateModel.withKeywords)
                                    }

                                }else{
                                    if(stateModel.isConnected){
                                        viewModel.defaultDisconnect(stateModel.withKeywords)
                                    }
                                    viewModel.timeDisconnect()
                                    if(stateModel.isConnected){
                                        viewModel.defaultConnect(stateModel.withKeywords)
                                    }
                                }
                                              },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Colors().buttonColor
                            )
                        )
                    }
                }
            }

        }
    }
}