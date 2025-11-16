package com.muggle.tiktokcopy.business.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.CaptchaCodeEditor
import com.muggle.tiktokcopy.ui.component.CaptchaCodePrivacyWidget
import com.muggle.tiktokcopy.ui.component.ConfirmButton
import com.muggle.tiktokcopy.ui.component.LoginToolBar
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp
import kotlinx.coroutines.delay

/**
 * 输入验证码界面
 */
@Composable
fun InputCaptchaCodePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.cdp)
    ) {
        LoginToolBar(R.drawable.common_left, "帮助")
        Spacer(modifier = Modifier.height(28.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "请输入验证码",
            fontSize = 24.csp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.cdp))
        SendMessageHint()
        Spacer(modifier = Modifier.height(27.cdp))
        CaptchaCodeEditor()
        Spacer(modifier = Modifier.height(33.cdp))
        ConfirmButton(isClickable = false, hintText = "登录")
        Spacer(modifier = Modifier.height(24.cdp))
        CaptchaCodeExceptionAction(60 * 1000)
    }
}

/**
 * 验证码异常操作
 */
@Composable
fun CaptchaCodeExceptionAction(countDownMillis: Long = 60 * 1000) {
    var countDownMs by remember {
        mutableLongStateOf(countDownMillis)
    }

    LaunchedEffect(Unit) {
        while (countDownMs > 0) {
            delay(1000)
            countDownMs -= 1000
        }
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "收不到验证码？",
            color = Color(0x6604498d),
            fontSize = 14.csp
        )
        // TODO: 发送验证码和语音验证
        if (countDownMs > 0) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "${countDownMs / 1000}秒后重新发送",
                color = Color(0x7fababaf),
                fontSize = 14.csp
            )
        } else {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "重新发送",
                color = Color(0x6604498d),
                fontSize = 14.csp
            )
        }
    }

}

@Composable
fun SendMessageHint() {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "短信已发送至",
            fontSize = 14.csp,
            color = Color(0x7fababaf),
            style = TextStyle(baselineShift = BaselineShift.Subscript)
        )
        Spacer(modifier = Modifier.width(4.cdp))
        // TODO: 替换为真实的手机号码
        Text(
            text = "+86 182 1483 9928",
            fontSize = 18.csp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = TextStyle(baselineShift = BaselineShift.Subscript)
        )
    }
}

@Preview
@Composable
fun PreviewInputCaptchaCodePage() {
    InputCaptchaCodePage()
}