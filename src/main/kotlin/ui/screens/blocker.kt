package ui.screens
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.blocker.blockerListModel
import data.blocker.blockerModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import styles.Colors
import styles.RoveTypography
import java.awt.Toolkit

@Composable
fun blocker(
    viewModel: blockerModel
) {

    val configuration= Toolkit.getDefaultToolkit().screenSize
    val width = configuration.width
    val height= configuration.height



    val snackstate= remember { mutableStateOf(false) }


    val stateModel by viewModel.blockerState.collectAsState()

    val listViewmodel= remember { blockerListModel() }
    val blockerListStat by listViewmodel.blockerListState.collectAsState()

    if(!stateModel.once){
        val query= "SELECT * FROM user"
        val initiater= stateModel.connection.prepareStatement(query)
        var rs = initiater.executeQuery()




        while (rs.next()){
            blockerListStat.textlist.add(rs.getString("keywords"))

        }

        for(value in blockerListStat.textlist){
            print(value+"\n")
        }


        initiater.close()
        viewModel.updateRun()
    }






    if(snackstate.value){
        dialog(
            onDismiss = { snackstate.value = false }
        )
    }

    MaterialTheme (){
        Column (
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier.height((height*0.23).dp).width((width*0.25).dp).
                background(Color(0xff4A4A4A), shape = RoundedCornerShape(30.dp)),


                ){
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    contentPadding = PaddingValues(15.dp)
                ) {
                    print(blockerListStat.textlist.size)
                    items(blockerListStat.textlist.size) {index->
                        Box(
                            modifier = Modifier.fillMaxWidth(0.9f).height((height*0.05).dp)
                        ){

                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Text(blockerListStat.textlist[index], style = RoveTypography.body1)
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = {
                                        listViewmodel.deleteValue(index)
                                    }
                                ){
                                    Icon(
                                        Icons.Default.Close,
                                        tint = Color(0xffffffff),
                                        contentDescription = "",
                                    )
                                }
                            }


                        }
                        Divider(
                            color = Color(0xffffffff),
                            thickness = 1.dp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(25.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(0.5f).height(45.dp),
                value = stateModel.keywordsFieldState,
                onValueChange = {
                    viewModel.updateKeywords(it)
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Info,
                        tint = Color(0xffffffff),
                        contentDescription = "",
                    )
                },
                placeholder = { Text("Enter Keywords or URL and separate each by ;", style = RoveTypography.body1, color = Color(0xffffffff)) },
                textStyle = RoveTypography.body1,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color(0xff555555),
                    textColor = Color(0xffffffff),
                    cursorColor = Color(0xffffffff)
                ),
                shape = RoundedCornerShape(30.dp),


                )
            Spacer(modifier = Modifier.size(20.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(0.23f)
                    .height(45.dp)
                    .padding(bottom = 5.dp)
                ,
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Colors().buttonColor
                ),
                onClick = {
                    val values= stateModel.keywordsFieldState+";"
                    val word=values.split(";")
                    val words=word.subList(0,word.size-1)


                    for( value in words){

                        val dupcheck= "SELECT *\n" +
                                "FROM user\n" +
                                "WHERE user.keywords LIKE '$value';"

                        val dup = stateModel.connection.prepareStatement(dupcheck)
                        var duplicate= dup.executeQuery()
                        if (duplicate.next()){
                            snackstate.value=true



                        }
                        else{
                            val statement = "INSERT INTO user VALUES('$value') "
                            val prep= stateModel.connection.prepareStatement(statement)
                            listViewmodel.addValue(value)
                            prep.executeUpdate()
                            prep.close()
                        }
                        duplicate.close()


                    }




                }
            ){
                Text(
                    text = "Block",
                    style = RoveTypography.body1
                )
            }




        }}


}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun dialog(onDismiss: () -> Unit) {
    // Your dialog content
    AlertDialog(
        backgroundColor = Color(0xff555555),
        onDismissRequest = onDismiss,
        title = { Text("Duplicate Value!") },
        shape = RoundedCornerShape(20.dp),
        text = { Text("The database already contains this value") },
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

