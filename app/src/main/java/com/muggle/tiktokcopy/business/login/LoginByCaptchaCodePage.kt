package com.muggle.tiktokcopy.business.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.login.intent.LoginByCaptchaCodeEvent
import com.muggle.tiktokcopy.business.login.vm.LoginByCaptchaCodeVm
import com.muggle.tiktokcopy.ui.component.login.CaptchaCodePrivacyWidget
import com.muggle.tiktokcopy.ui.component.login.ConfirmButton
import com.muggle.tiktokcopy.ui.component.login.LoginToolBar
import com.muggle.tiktokcopy.ui.component.login.PhoneNumberEditor
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun LoginByCaptchaCodePage(loginByCaptchaCodeVm: LoginByCaptchaCodeVm = hiltViewModel()) {

    val curState by remember {
        loginByCaptchaCodeVm.loginByCaptchaCodeState
    }

    val isConfirmEnable by remember {
        derivedStateOf { curState.isConfirmBtnEnable }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.cdp)
    ) {
        LoginToolBar(
            resId = R.drawable.common_left,
            hintText = "帮助",
            onClickBack = {
                loginByCaptchaCodeVm.onReceiveEvent(LoginByCaptchaCodeEvent.ClickBackBtn)
            },
            onClickHelp = {
                loginByCaptchaCodeVm.onReceiveEvent(LoginByCaptchaCodeEvent.ClickHelpBtn)
            }
        )
        Spacer(modifier = Modifier.height(28.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "登录后即可评论",
            fontSize = 24.csp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "未注册的手机号，验证通过后将自动注册",
            fontSize = 14.csp,
            color = Color(0x7fababaf)
        )
        Spacer(modifier = Modifier.height(24.cdp))
        PhoneNumberEditor(
            phoneNumber = curState.phoneNumber,
            regionCode = curState.regionCode,
            onTextChangeAct = {
                loginByCaptchaCodeVm.onReceiveEvent(LoginByCaptchaCodeEvent.InputPhoneNumber(it))
            },
            onClearAct = {
                loginByCaptchaCodeVm.onReceiveEvent(LoginByCaptchaCodeEvent.InputPhoneNumber(""))
            },
            onClickRegionCodeAct = {
                loginByCaptchaCodeVm.onReceiveEvent(LoginByCaptchaCodeEvent.ClickChangeRegionCode)
            }
        )
        Spacer(modifier = Modifier.height(8.cdp))
        SwitchToPasswordWidget {
            loginByCaptchaCodeVm.onReceiveEvent(LoginByCaptchaCodeEvent.ClickLoginByPassword)
        }
        Spacer(modifier = Modifier.height(24.cdp))
        ConfirmButton(
            isClickable = isConfirmEnable,
            hintText = "验证并登录",
            onConfirm = {
                loginByCaptchaCodeVm.onReceiveEvent(LoginByCaptchaCodeEvent.ClickConfirmBtn)
            }
        )
        Spacer(modifier = Modifier.height(24.cdp))
        CaptchaCodePrivacyWidget(
            isSelect = curState.isPrivacySelect,
            onSelectPrivacyAct = {
                loginByCaptchaCodeVm.onReceiveEvent(LoginByCaptchaCodeEvent.ClickPrivacySelect(it))
            }
        )

    }
}

/**
 * 切换到密码登录
 */
@Composable
fun SwitchToPasswordWidget(onClickLoginByPassword: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .wrapContentSize()
            .clickable {
                onClickLoginByPassword()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(14.cdp)
                .alpha(0.4f),
            painter = painterResource(R.drawable.common_switch),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(3.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "密码登录",
            color = Color(0x6604498d),
            fontSize = 14.csp
        )
    }
}

@Preview
@Composable
fun PreviewLoginByCaptchaCode() {
    LoginByCaptchaCodePage()
}