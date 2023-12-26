package ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.button.button
import data.home.homeModel
import styles.RoveTypography
import java.awt.Toolkit

@Composable
@Preview
fun home(
   viewModel: homeModel,

) {
    val configuration= Toolkit.getDefaultToolkit().screenSize
    val width = configuration.width
    val height= configuration.height

    val stateModel by viewModel.homeState.collectAsState()

    MaterialTheme(

    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
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
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                button(
                    onClick = {
                        if(stateModel.isConnected){
                            viewModel.defaultDisconnect()
                        } else {
                            viewModel.defaultConnect()

                        }
                    },
                    text = stateModel.button
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stateModel.status,
                    style = RoveTypography.body2
                )



            }

        }
    }
}