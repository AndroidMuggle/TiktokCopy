package com.muggle.tiktokcopy.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.login.intent.FindPasswordEvent
import com.muggle.tiktokcopy.business.login.vm.FindPasswordPageVm
import com.muggle.tiktokcopy.ui.component.login.ConfirmButton
import com.muggle.tiktokcopy.ui.component.login.FindPasswordEditor
import com.muggle.tiktokcopy.ui.component.login.LoginToolBar
import com.muggle.tiktokcopy.ui.component.login.PrivacyConfirmWidget
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun FindPasswordScreen(findPasswordPageVm: FindPasswordPageVm = hiltViewModel()) {

    val curState by findPasswordPageVm.findPasswordUiState.collectAsStateWithLifecycle()

    val isConfirmBtnEnable by remember {
        derivedStateOf {
            curState.isConfirmBtnEnable
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.cdp)
    ) {
        LoginToolBar(
            resId = R.drawable.common_left,
            hintText = "",
            onClickBack = {
                findPasswordPageVm.onReceiveEvent(FindPasswordEvent.ClickBackBtn)
            }
        )
        Spacer(modifier = Modifier.height(45.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "找回密码",
            fontSize = 24.csp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(11.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "短信已发送至${curState.phoneNumber}",
            fontSize = 14.csp,
            color = Color(0x7fababaf),
            style = TextStyle(baselineShift = BaselineShift.Subscript)
        )
        Spacer(modifier = Modifier.height(11.cdp))
        FindPasswordEditor(
            curInput = curState.captchaCode,
            onCaptchaCodeChange = {
                findPasswordPageVm.onReceiveEvent(FindPasswordEvent.InputCaptchaCode(it))
            },
            onClickResend = {
                findPasswordPageVm.onReceiveEvent(FindPasswordEvent.ClickResendBtn)
            }
        )
        Spacer(modifier = Modifier.height(8.cdp))
        PrivacyConfirmWidget(
            isSelected = curState.isPrivacySelect,
            horizontal = Arrangement.Start,
        ) {
            findPasswordPageVm.onReceiveEvent(FindPasswordEvent.ClickPrivacyBtn(it))
        }
        Spacer(modifier = Modifier.height(16.cdp))
        ConfirmButton(isClickable = isConfirmBtnEnable, hintText = "完成") {
            findPasswordPageVm.onReceiveEvent(FindPasswordEvent.ClickConfirmBtn)
        }
    }
}

@Preview
@Composable
fun PreviewFindPasswordScreen() {
    FindPasswordScreen()
}