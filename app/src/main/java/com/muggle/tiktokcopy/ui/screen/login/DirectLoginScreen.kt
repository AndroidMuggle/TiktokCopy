package com.muggle.tiktokcopy.ui.screen.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.login.intent.LoginDirectEvent
import com.muggle.tiktokcopy.business.login.vm.DirectLoginVm
import com.muggle.tiktokcopy.ui.component.login.ConfirmButton
import com.muggle.tiktokcopy.ui.component.login.LoginToolBar
import com.muggle.tiktokcopy.ui.component.login.PrivacyConfirmWidget
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun DirectLoginScreen(directLoginVm: DirectLoginVm = hiltViewModel()) {

    val curState by remember {
        directLoginVm.directLoginUiState
    }

    val isConfirmBtnEnable by remember {
        derivedStateOf { curState.isConfirmBtnEnable }
    }

    LaunchedEffect(Unit) {
        // TODO: 进入页面读取缓存数据
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.cdp)
    ) {
        LoginToolBar(
            resId = R.drawable.common_close,
            hintText = "帮助",
            onClickBack = {
                directLoginVm.onReceiveEvent(LoginDirectEvent.ClickBackBtn)
            },
            onClickHelp = {
                directLoginVm.onReceiveEvent(LoginDirectEvent.ClickHelpBtn)
            }
        )
        Spacer(modifier = Modifier.height(60.cdp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = "登录后，体验完整功能",
            fontSize = 28.csp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xff161823)
        )
        Spacer(modifier = Modifier.height(125.cdp))
        AsyncImage(
            modifier = Modifier
                .size(140.cdp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    if (curState.userAvatar.isNotEmpty()) {
                        curState.userAvatar
                    } else {
                        R.drawable.common_checked
                    }
                )
                .build(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.common_checked),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(18.cdp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = curState.userName,
            fontSize = 28.csp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            color = Color(0xff161823)
        )
        Spacer(modifier = Modifier.height(48.cdp))
        ConfirmButton(
            isClickable = isConfirmBtnEnable,
            hintText = "一键登录",
            onConfirm = {
                directLoginVm.onReceiveEvent(LoginDirectEvent.ClickConfirmBtn)
            }
        )
        Spacer(modifier = Modifier.height(18.cdp))
        PrivacyConfirmWidget(isConfirmBtnEnable) {
            directLoginVm.onReceiveEvent(LoginDirectEvent.ClickPrivacyBtn(it))
        }
        Spacer(modifier = Modifier.height(149.cdp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    directLoginVm.onReceiveEvent(LoginDirectEvent.ClickChangeAccount)
                },
            text = "登录其他账号",
            color = Color(0x6604498d),
            fontSize = 14.csp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewDirectLoginScreen() {
    DirectLoginScreen()
}