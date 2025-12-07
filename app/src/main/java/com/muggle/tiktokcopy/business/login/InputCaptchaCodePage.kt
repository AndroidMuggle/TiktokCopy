package com.muggle.tiktokcopy.business.login

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.derivedStateOf
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.login.intent.InputCaptchaCodeEvent
import com.muggle.tiktokcopy.business.login.vm.InputCaptchaCodeVm
import com.muggle.tiktokcopy.ui.component.CaptchaCodeEditor
import com.muggle.tiktokcopy.ui.component.ConfirmButton
import com.muggle.tiktokcopy.ui.component.LoginToolBar
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp
import kotlinx.coroutines.delay

/**
 * 输入验证码界面
 */
@Composable
fun InputCaptchaCodePage(inputCaptchaCodeVm: InputCaptchaCodeVm = hiltViewModel()) {

    val curState by remember {
        mutableStateOf(inputCaptchaCodeVm.inputCaptchaCodeState)
    }

    val isConfirmEnable by remember {
        derivedStateOf { curState.value.isConfirmBtnEnable }
    }

    LaunchedEffect(Unit) {
        // TODO: 读取或输入初始值
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
                inputCaptchaCodeVm.onReceiveEvent(InputCaptchaCodeEvent.ClickBackBtn)
            },
            onClickHelp = {
                inputCaptchaCodeVm.onReceiveEvent(InputCaptchaCodeEvent.ClickHelpBtn)
            }
        )
        Spacer(modifier = Modifier.height(28.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "请输入验证码",
            fontSize = 24.csp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.cdp))
        SendMessageHint(phoneNumber = curState.value.phoneNumber)
        Spacer(modifier = Modifier.height(27.cdp))
        CaptchaCodeEditor(
            inputCode = curState.value.captchaCode,
            onCaptchaCodeChange = {
                inputCaptchaCodeVm.onReceiveEvent(InputCaptchaCodeEvent.InputCaptchaCode(it))
            }
        )
        Spacer(modifier = Modifier.height(33.cdp))
        ConfirmButton(
            isClickable = isConfirmEnable,
            hintText = "登录",
            onConfirm = {
                inputCaptchaCodeVm.onReceiveEvent(InputCaptchaCodeEvent.ClickConfirmBtn)
            }
        )
        Spacer(modifier = Modifier.height(24.cdp))
        CaptchaCodeExceptionAction(
            countDownMillis = 60 * 1000,
            onClickCannotReceive = {
                inputCaptchaCodeVm.onReceiveEvent(InputCaptchaCodeEvent.ClickCannotReceiveCode)
            },
            onClickResend = {
                inputCaptchaCodeVm.onReceiveEvent(InputCaptchaCodeEvent.ClickResendCode)
            }
        )
    }
}

/**
 * 验证码异常操作
 */
@Composable
fun CaptchaCodeExceptionAction(
    countDownMillis: Long = 60 * 1000,
    onClickCannotReceive: () -> Unit = {},
    onClickResend: () -> Unit = {}
) {
    var countDownMs by remember {
        mutableLongStateOf(countDownMillis)
    }

    LaunchedEffect(countDownMs) {
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
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onClickCannotReceive()
                },
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
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        countDownMs = countDownMillis
                        onClickResend()
                    },
                text = "重新发送",
                color = Color(0x6604498d),
                fontSize = 14.csp
            )
        }
    }

}

@Composable
fun SendMessageHint(phoneNumber: String = "") {
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
            text = phoneNumber,
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