package com.muggle.tiktokcopy.business.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.muggle.tiktokcopy.business.login.intent.LoginByPasswordEvent
import com.muggle.tiktokcopy.business.login.vm.LoginByPasswordVm
import com.muggle.tiktokcopy.ui.component.ConfirmButton
import com.muggle.tiktokcopy.ui.component.LoginToolBar
import com.muggle.tiktokcopy.ui.component.PasswordInputBar
import com.muggle.tiktokcopy.ui.component.PhoneNumberEditor
import com.muggle.tiktokcopy.ui.component.PrivacyConfirmWidget
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun LoginByPasswordPage(loginByPasswordVm: LoginByPasswordVm = hiltViewModel()) {

    val curState by remember {
        loginByPasswordVm.loginByPasswordState
    }

    val isConfirmBtnEnable by remember {
        derivedStateOf { curState.isConfirmEnable }
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
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClickBackBtn)
            },
            onClickHelp = {
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClickHelpBtn)
            }
        )
        Spacer(modifier = Modifier.height(45.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "手机号码密码登录",
            fontSize = 24.csp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.cdp))
        PhoneNumberEditor(
            phoneNumber = curState.curPhoneNumber,
            regionCode = curState.regionCode,
            onTextChangeAct = {
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.InputPhoneNumber(it))
            },
            onClearAct = {
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClearPhoneNumber)
            },
            onClickRegionCodeAct = {
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClickRegionCode)
            }
        )
        Spacer(modifier = Modifier.height(12.cdp))
        PasswordInputBar(
            password = curState.curPassword,
            passwordVisibility = curState.isPasswordVisible,
            onClearPassword = {
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClearPassword)
            },
            onPasswordChangeAct = {
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.InputPassword(it))
            },
            onChangePasswordVisibility = {
                loginByPasswordVm.onReceiveEvent(
                    LoginByPasswordEvent.ClickChangePasswordVisibility(
                        it
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(8.cdp))
        SwitchLoginWidget(
            onClickCaptchaLogin = {
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClickCaptchaLogin)
            },
            onClickForgetPassword = {
                loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClickForgetPassword)
            }
        )
        Spacer(modifier = Modifier.height(24.cdp))
        ConfirmButton(isConfirmBtnEnable) {
            loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClickConfirmBtn)
        }
        Spacer(modifier = Modifier.height(24.cdp))
        PrivacyConfirmWidget(curState.isPrivacySelected) {
            loginByPasswordVm.onReceiveEvent(LoginByPasswordEvent.ClickConfirmPrivacy(it))
        }
    }
}

/**
 * 切换登录方式插件
 */
@Composable
fun SwitchLoginWidget(
    onClickCaptchaLogin: () -> Unit = {},
    onClickForgetPassword: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onClickCaptchaLogin()
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
                text = "验证码登录",
                color = Color(0x6604498d),
                fontSize = 14.csp
            )
        }

        Text(
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onClickForgetPassword()
                },
            text = "忘记密码",
            color = Color(0x6604498d),
            fontSize = 14.csp
        )
    }
}


@Preview
@Composable
fun PreviewLoginByPassword() {
    LoginByPasswordPage()
}