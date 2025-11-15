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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.ConfirmButton
import com.muggle.tiktokcopy.ui.component.LoginToolBar
import com.muggle.tiktokcopy.ui.component.PasswordInputBar
import com.muggle.tiktokcopy.ui.component.PhoneNumberEditor
import com.muggle.tiktokcopy.ui.component.PrivacyConfirmWidget
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun LoginByPassword() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.cdp)
    ) {
        LoginToolBar(R.drawable.common_left, "帮助")
        Spacer(modifier = Modifier.height(45.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "手机号码密码登录",
            fontSize = 24.csp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.cdp))
        PhoneNumberEditor()
        Spacer(modifier = Modifier.height(11.cdp))
        PasswordInputBar()
        Spacer(modifier = Modifier.height(9.cdp))
        SwitchLoginWidget()
        Spacer(modifier = Modifier.height(24.cdp))
        ConfirmButton()
        Spacer(modifier = Modifier.height(24.cdp))
        PrivacyConfirmWidget()
    }
}

/**
 * 切换登录方式插件
 */
@Composable
fun SwitchLoginWidget() {
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
                    // TODO: 点击切换到验证码登录
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
                    // TODO: 点击跳转到忘记密码页面
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
    LoginByPassword()
}