package com.muggle.tiktokcopy.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muggle.tiktokcopy.ui.component.nav.BottomNavigator
import com.muggle.tiktokcopy.utils.cdp

/**
 * @author Muggle
 * @date 2026/2/16 23:52
 * @desc
 **/
@Composable
fun AppScreen() {
    val controller = rememberNavController()
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(84.cdp)
                .fillMaxWidth()
                .background(color = Color(0xff181818))
                .align(alignment = Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            BottomNavigator(navController = controller)
        }

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = controller,
            startDestination = HomePage
        ) {
            composable<HomePage> {
                HomeScreen()
            }
            composable<FriendPage> {
                FriendScreen()
            }
            composable<CreateVideoPage> {
                CreateVideoScreen()
            }
            composable<MessagePage> {
                MessageScreen()
            }
            composable<MinePage> {
                MineScreen()
            }
        }
    }
}