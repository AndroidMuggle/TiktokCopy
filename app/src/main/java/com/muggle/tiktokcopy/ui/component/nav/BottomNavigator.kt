package com.muggle.tiktokcopy.ui.component.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.bean.HomePageClickType
import com.muggle.tiktokcopy.business.home.intent.BottomNavigatorClickAct
import com.muggle.tiktokcopy.business.home.state.BottomNavUiState
import com.muggle.tiktokcopy.business.home.vm.BottomNavigatorVm
import com.muggle.tiktokcopy.ui.component.common.constants.AppConst
import com.muggle.tiktokcopy.ui.screen.HomePage
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2025/12/8 22:49
 * @author muggle
 * @desc
 */
@Composable
fun BottomNavigator(
    bottomNavigatorVm: BottomNavigatorVm = hiltViewModel(),
    navController: NavController = rememberNavController()
) {
    val curState by bottomNavigatorVm.bottomNavUiState.collectAsStateWithLifecycle()

    BottomNavigator(
        state = curState,
        onClickHomePage = {
            navController.navigate(
                route = HomePage,
                navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
            )

            when (bottomNavigatorVm.bottomNavUiState.value.curHomePageVideoType) {
                HomePageClickType.SingleVideo -> {
                    if (curState.bottomNavigatorState[0].isSelected) {
                        bottomNavigatorVm.onReceiveBottomNaviClickAct(
                            BottomNavigatorClickAct.ClickHomePage(HomePageClickType.VideoList)
                        )
                    } else {
                        bottomNavigatorVm.onReceiveBottomNaviClickAct(
                            BottomNavigatorClickAct.ClickHomePage(HomePageClickType.SingleVideo)
                        )
                    }
                }

                HomePageClickType.VideoList -> {
                    if (curState.bottomNavigatorState[0].isSelected) {
                        bottomNavigatorVm.onReceiveBottomNaviClickAct(
                            BottomNavigatorClickAct.ClickHomePage(HomePageClickType.SingleVideo)
                        )
                    } else {
                        bottomNavigatorVm.onReceiveBottomNaviClickAct(
                            BottomNavigatorClickAct.ClickHomePage(HomePageClickType.VideoList)
                        )
                    }
                }
            }
        },
        onClickFriendPage = {
            bottomNavigatorVm.onReceiveBottomNaviClickAct(BottomNavigatorClickAct.ClickFriendPage)
        },
        onClickCreateVideoPage = {
            bottomNavigatorVm.onReceiveBottomNaviClickAct(BottomNavigatorClickAct.ClickCreateVideoPage)
        },
        onClickMessagePage = {
            bottomNavigatorVm.onReceiveBottomNaviClickAct(BottomNavigatorClickAct.ClickMessagePage)
        },
        onClickMinePage = {
            bottomNavigatorVm.onReceiveBottomNaviClickAct(BottomNavigatorClickAct.ClickMinePage)
        }
    )
}

@Composable
internal fun BottomNavigator(
    state: BottomNavUiState = BottomNavUiState(),
    onClickHomePage: () -> Unit = {},
    onClickFriendPage: () -> Unit = {},
    onClickCreateVideoPage: () -> Unit = {},
    onClickMessagePage: () -> Unit = {},
    onClickMinePage: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 11.cdp)
            .height(84.cdp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .width(58.cdp)
                .clickable {
                    onClickHomePage()
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.bottomNavigatorState[0].navName == AppConst.NAV_NAME_HOME || !state.bottomNavigatorState[0].isSelected) {
                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = AppConst.NAV_NAME_HOME,
                    fontSize = 20.csp,
                    fontWeight = FontWeight.Bold,
                    color = if (state.bottomNavigatorState[0].isSelected) {
                        Color(0xffffffff)
                    } else {
                        Color(0xff979797)
                    }
                )
                if (state.bottomNavigatorState[0].isSelected) {
                    Image(
                        modifier = Modifier.size(10.cdp),
                        painter = painterResource(state.bottomNavigatorState[0].navIcon),
                        contentDescription = null
                    )
                }
            } else {
                if (state.bottomNavigatorState[0].isSelected) {
                    Image(
                        modifier = Modifier.size(10.cdp),
                        painter = painterResource(state.bottomNavigatorState[0].navIcon),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.wrapContentSize(),
                    fontSize = 20.csp,
                    fontWeight = FontWeight.Bold,
                    text = AppConst.NAV_NAME_BACK,
                    color = if (state.bottomNavigatorState[0].isSelected) {
                        Color(0xffffffff)
                    } else {
                        Color(0xff979797)
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(58.cdp)
                .clickable {
                    onClickFriendPage()
                }
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.Center),
                text = state.bottomNavigatorState[1].navName,
                fontSize = 20.csp,
                fontWeight = FontWeight.Bold,
                color = if (state.bottomNavigatorState[1].isSelected) {
                    Color(0xffffffff)
                } else {
                    Color(0xff979797)
                }
            )

            if (state.bottomNavigatorState[1].avatarIcon.isNotEmpty()) {
                AsyncImage(
                    modifier = Modifier
                        .size(18.cdp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.TopEnd),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            state.bottomNavigatorState[1].avatarIcon.ifEmpty {
                                R.drawable.common_nav_user_avatar_holder
                            }
                        )
                        .build(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.common_nav_user_avatar_holder),
                    contentDescription = ""
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(58.cdp)
                .clickable {
                    onClickCreateVideoPage()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(37.cdp),
                painter = painterResource(state.bottomNavigatorState[2].navIcon),
                contentDescription = null
            )
        }



        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(58.cdp)
                .clickable {
                    onClickMessagePage()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.Center),
                text = state.bottomNavigatorState[3].navName,
                fontSize = 20.csp,
                fontWeight = FontWeight.Bold,
                color = if (state.bottomNavigatorState[3].isSelected) {
                    Color(0xffffffff)
                } else {
                    Color(0xff979797)
                }
            )
            if (state.bottomNavigatorState[3].newMessageCount > 0) {
                Text(
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.TopEnd)
                        .background(color = Color(0xfffe2c55), shape = CircleShape),
                    text = state.bottomNavigatorState[3].newMessageCount.toString(),
                    color = Color.White,
                    fontSize = 18.csp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(58.cdp)
                .clickable {
                    onClickMinePage()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = state.bottomNavigatorState[4].navName,
                fontSize = 20.csp,
                fontWeight = FontWeight.Bold,
                color = if (state.bottomNavigatorState[4].isSelected) {
                    Color(0xffffffff)
                } else {
                    Color(0xff979797)
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewBottomNavigator() {
    BottomNavigator()
}

val NAVIGATOR_DEFAULT_LIST =
    listOf<SingleBottomNavigatorState>(
        SingleBottomNavigatorState(
            navName = AppConst.NAV_NAME_HOME,
            navIcon = R.drawable.common_nav_switch,
            isSelected = true,
            newMessageCount = 0,
            avatarIcon = ""
        ),
        SingleBottomNavigatorState(
            navName = AppConst.NAV_NAME_FRIEND,
            navIcon = R.drawable.common_nav_left,
            isSelected = false,
            newMessageCount = 0,
            avatarIcon = ""
        ),
        SingleBottomNavigatorState(
            navName = AppConst.NAV_NAME_CREATION,
            navIcon = R.drawable.common_nav_add,
            isSelected = false,
            newMessageCount = 0,
            avatarIcon = ""
        ), SingleBottomNavigatorState(
            navName = AppConst.NAV_NAME_MESSAGE,
            navIcon = 0,
            isSelected = false,
            newMessageCount = 0,
            avatarIcon = ""
        ), SingleBottomNavigatorState(
            navName = AppConst.NAV_NAME_ME,
            navIcon = 0,
            isSelected = false,
            newMessageCount = 0,
            avatarIcon = ""
        )

    )
