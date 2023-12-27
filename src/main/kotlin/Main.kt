import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.blocker.blockerModel
import data.home.homeModel
import data.main.mainModel
import data.restrict.restrictModel
import styles.Colors
import styles.RoveTypography
import ui.screens.blocker
import ui.screens.home
import ui.screens.restrict
import java.awt.Dimension
import java.awt.Toolkit

@Composable
fun App(){
    main()
}



fun main() = application {
    val homeModel = remember { homeModel() }
    val blockerModel= remember { blockerModel() }
    val restrictModel= remember { restrictModel() }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Blocker",

        ) {

        val configuration = Toolkit.getDefaultToolkit().screenSize
        val width = configuration.width
        val height = configuration.height
        window.minimumSize = Dimension((width * 0.6).toInt(), (height * 0.7).toInt())
        window.maximumSize = Dimension(width, height)
        val viewModel = remember { mainModel() }
        val stateModel by viewModel.mainState.collectAsState()

        var visible by remember { mutableStateOf(true) }


        MaterialTheme {


            Row(
                modifier = Modifier.fillMaxSize().background(color = Colors().primaryDark)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(0.22f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size((height * 0.018).dp))
                    Row(


                    ) {

                        TextField(
                            modifier = Modifier.fillMaxWidth(0.9f).height(45.dp),
                            value = stateModel.search,
                            onValueChange = {
                                viewModel.update(it)
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    tint = Color(0xffffffff),
                                    contentDescription = "",
                                )
                            },
                            placeholder = { Text("Search", style = RoveTypography.body1, color = Color(0xffffffff)) },
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


                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Divider(
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                            .fillMaxWidth()
                            .width(1.dp)
                            .background(color = Color(0xff4D4D4D))
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(0.2f))


                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(0.9f).height(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (stateModel.home) {
                                Colors().buttonColor

                            } else {
                                Color.Transparent
                            },

                            ),
                        contentPadding = PaddingValues(0.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        onClick = {
                            viewModel.home()
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(

                                Icons.Default.Home,
                                tint = if (stateModel.home) {
                                    Color(0xffffffff)
                                } else {
                                    Color(0xff737373)
                                },
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                "Home",
                                style = if (stateModel.home) {
                                    RoveTypography.body1
                                } else {
                                    RoveTypography.body2
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.03f))


                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(0.9f).height(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (stateModel.blocker) {
                                Colors().buttonColor

                            } else {
                                Color.Transparent
                            },

                            ),
                        contentPadding = PaddingValues(0.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        onClick = {

                            viewModel.blocker()
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(

                                Icons.Default.Clear,
                                tint = if (stateModel.blocker) {
                                    Color(0xffffffff)
                                } else {
                                    Color(0xff737373)
                                },
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                "Blocker",
                                style = if (stateModel.blocker) {
                                    RoveTypography.body1
                                } else {
                                    RoveTypography.body2
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(0.9f).height(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (stateModel.restrict) {
                                Colors().buttonColor

                            } else {
                                Color.Transparent
                            },

                            ),
                        contentPadding = PaddingValues(0.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        onClick = {
                            viewModel.restrict()
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(

                                Icons.Default.Home,
                                tint = if (stateModel.restrict) {
                                    Color(0xffffffff)
                                } else {
                                    Color(0xff737373)
                                },
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                "Restrict",
                                style = if (stateModel.restrict) {
                                    RoveTypography.body1
                                } else {
                                    RoveTypography.body2
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(0.9f).height(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (stateModel.summary) {
                                Colors().buttonColor

                            } else {
                                Color.Transparent
                            },

                            ),
                        contentPadding = PaddingValues(0.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        onClick = {
                            viewModel.summary()
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.7f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(

                                Icons.Default.Home,
                                tint = if (stateModel.summary) {
                                    Color(0xffffffff)
                                } else {
                                    Color(0xff737373)
                                },
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                "Summary",
                                style = if (stateModel.summary) {
                                    RoveTypography.body1
                                } else {
                                    RoveTypography.body2
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(0.9f).height(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (stateModel.monitor) {
                                Colors().buttonColor

                            } else {
                                Color.Transparent
                            },

                            ),
                        contentPadding = PaddingValues(0.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        onClick = {
                            viewModel.monitor()
                        }
                    ) {
                        Row(
                            modifier = Modifier.padding(0.dp).fillMaxWidth(0.7f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(

                                Icons.Default.Info,
                                tint = if (stateModel.monitor) {
                                    Color(0xffffffff)
                                } else {
                                    Color(0xff737373)
                                },
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                "Monitor",
                                style = if (stateModel.monitor) {
                                    RoveTypography.body1
                                } else {
                                    RoveTypography.body2
                                }
                            )
                        }


                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.03f))
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(0.9f).height(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (stateModel.settings) {
                                Colors().buttonColor

                            } else {
                                Color.Transparent
                            },

                            ),
                        contentPadding = PaddingValues(0.dp),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        onClick = {
                            viewModel.settings()
                        }
                    ) {
                        Row(
                            modifier = Modifier.padding(0.dp).fillMaxWidth(0.7f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {
                            Icon(

                                Icons.Default.Settings,
                                tint = if (stateModel.settings) {
                                    Color(0xffffffff)
                                } else {
                                    Color(0xff737373)
                                },
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                "Settings",
                                style = if (stateModel.settings) {
                                    RoveTypography.body1
                                } else {
                                    RoveTypography.body2
                                }
                            )
                        }
                    }

                    Column (
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

                        Divider(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp,bottom = 20.dp)
                                .fillMaxWidth()
                                .width(1.dp)
                                .background(color = Color(0xff4D4D4D))
                        )
                        Text(
                            "Version 2.0",
                            modifier = Modifier.padding(bottom = 20.dp),
                            style = RoveTypography.body2
                        )
                    }


                }


                    Box(
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(color = Color(0xff4D4D4D))
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    if (stateModel.home) {
                        blockerModel.reset()
                        AnimatedVisibility(
                            visibleState = MutableTransitionState(
                                initialState = false
                            ).apply { targetState = true },
                            modifier = Modifier,
                            enter = fadeIn(initialAlpha = 0f),
                            exit = fadeOut(),
                        ) {
                            home(homeModel)
                        }
                    } else if (stateModel.blocker) {
                        AnimatedVisibility(
                            visibleState = MutableTransitionState(
                                initialState = false
                            ).apply { targetState = true },
                            modifier = Modifier,
                            enter = fadeIn(initialAlpha = 0f),
                            exit = fadeOut(),
                        ) {
                            blocker(blockerModel)
                        }
                    }
                    else if(stateModel.restrict){
                        blockerModel.reset()
                        AnimatedVisibility(
                            visibleState = MutableTransitionState(
                                initialState = false
                            ).apply { targetState = true },
                            modifier = Modifier,
                            enter = fadeIn(initialAlpha = 0f),
                            exit = fadeOut(),
                        ) {
                            restrict(restrictModel,blockerModel)
                        }
                    }

                }


            }

        }

    }

