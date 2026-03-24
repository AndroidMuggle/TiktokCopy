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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.bean.HomePageClickType
import com.muggle.tiktokcopy.business.home.intent.BottomNavigatorClickAct
import com.muggle.tiktokcopy.business.home.vm.HomeScreenVm
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
    homeScreenVm: HomeScreenVm = hiltViewModel(),
    navController: NavController = rememberNavController()
) {

    val curState = remember {
        homeScreenVm.homeScreenState.value.bottomNavigatorState
    }

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
                    navController.navigate(
                        route = HomePage,
                        navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
                    )

                    when (homeScreenVm.homeScreenState.value.curHomePageVideoType) {
                        HomePageClickType.SingleVideo -> {
                            if (curState[0].isSelected) {
                                homeScreenVm.onReceiveBottomNaviClickAct(
                                    BottomNavigatorClickAct.ClickHomePage(HomePageClickType.VideoList)
                                )
                            } else {
                                homeScreenVm.onReceiveBottomNaviClickAct(
                                    BottomNavigatorClickAct.ClickHomePage(HomePageClickType.SingleVideo)
                                )
                            }
                        }

                        HomePageClickType.VideoList -> {
                            if (curState[0].isSelected) {
                                homeScreenVm.onReceiveBottomNaviClickAct(
                                    BottomNavigatorClickAct.ClickHomePage(HomePageClickType.SingleVideo)
                                )
                            } else {
                                homeScreenVm.onReceiveBottomNaviClickAct(
                                    BottomNavigatorClickAct.ClickHomePage(HomePageClickType.VideoList)
                                )
                            }
                        }
                    }
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (curState[0].navName == AppConst.NAV_NAME_HOME || !curState[0].isSelected) {
                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = AppConst.NAV_NAME_HOME,
                    fontSize = 20.csp,
                    fontWeight = FontWeight.Bold,
                    color = if (curState[0].isSelected) {
                        Color(0xffffffff)
                    } else {
                        Color(0xff979797)
                    }
                )
                if (curState[0].isSelected) {
                    Image(
                        modifier = Modifier.size(10.cdp),
                        painter = painterResource(curState[0].navIcon),
                        contentDescription = null
                    )
                }
            } else {
                if (curState[0].isSelected) {
                    Image(
                        modifier = Modifier.size(10.cdp),
                        painter = painterResource(curState[0].navIcon),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.wrapContentSize(),
                    fontSize = 20.csp,
                    fontWeight = FontWeight.Bold,
                    text = AppConst.NAV_NAME_BACK,
                    color = if (curState[0].isSelected) {
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
                    homeScreenVm.onReceiveBottomNaviClickAct(BottomNavigatorClickAct.ClickFriendPage)
                }
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.Center),
                text = curState[1].navName,
                fontSize = 20.csp,
                fontWeight = FontWeight.Bold,
                color = if (curState[1].isSelected) {
                    Color(0xffffffff)
                } else {
                    Color(0xff979797)
                }
            )

            if (curState[1].avatarIcon.isNotEmpty()) {
                AsyncImage(
                    modifier = Modifier
                        .size(18.cdp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.TopEnd),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            curState[1].avatarIcon.ifEmpty {
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
                    homeScreenVm.onReceiveBottomNaviClickAct(BottomNavigatorClickAct.ClickCreateVideoPage)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(37.cdp),
                painter = painterResource(curState[2].navIcon),
                contentDescription = null
            )
        }



        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(58.cdp)
                .clickable {
                    homeScreenVm.onReceiveBottomNaviClickAct(BottomNavigatorClickAct.ClickMessagePage)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.Center),
                text = curState[3].navName,
                fontSize = 20.csp,
                fontWeight = FontWeight.Bold,
                color = if (curState[3].isSelected) {
                    Color(0xffffffff)
                } else {
                    Color(0xff979797)
                }
            )
            if (curState[3].newMessageCount > 0) {
                Text(
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.TopEnd)
                        .background(color = Color(0xfffe2c55), shape = CircleShape),
                    text = curState[3].newMessageCount.toString(),
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
                    homeScreenVm.onReceiveBottomNaviClickAct(BottomNavigatorClickAct.ClickMinePage)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = curState[4].navName,
                fontSize = 20.csp,
                fontWeight = FontWeight.Bold,
                color = if (curState[4].isSelected) {
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
    mutableStateListOf<BottomNavigatorState>(
        BottomNavigatorState(
            navName = AppConst.NAV_NAME_HOME,
            navIcon = R.drawable.common_nav_switch,
            isSelected = true,
            newMessageCount = 0,
            avatarIcon = ""
        ),
        BottomNavigatorState(
            navName = AppConst.NAV_NAME_FRIEND,
            navIcon = R.drawable.common_nav_left,
            isSelected = false,
            newMessageCount = 0,
            avatarIcon = ""
        ),
        BottomNavigatorState(
            navName = AppConst.NAV_NAME_CREATION,
            navIcon = R.drawable.common_nav_add,
            isSelected = false,
            newMessageCount = 0,
            avatarIcon = ""
        ), BottomNavigatorState(
            navName = AppConst.NAV_NAME_MESSAGE,
            navIcon = 0,
            isSelected = false,
            newMessageCount = 0,
            avatarIcon = ""
        ), BottomNavigatorState(
            navName = AppConst.NAV_NAME_ME,
            navIcon = 0,
            isSelected = false,
            newMessageCount = 0,
            avatarIcon = ""
        )

    )
