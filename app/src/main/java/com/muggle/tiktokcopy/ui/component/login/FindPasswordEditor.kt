package com.muggle.tiktokcopy.ui.component.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp
import kotlinx.coroutines.delay

@Composable
fun FindPasswordEditor(
    curInput: String = "",
    countDownMillis: Long = 60 * 1000,
    onCaptchaCodeChange: (String) -> Unit = {},
    onClickResend:()-> Unit = {}
) {
    var curInputStr by remember {
        mutableStateOf(curInput)
    }

    var countDownMs by remember {
        mutableLongStateOf(countDownMillis)
    }

    val focusRequester = remember {
        FocusRequester()
    }

    val softKeyBoard = LocalSoftwareKeyboardController.current

    LaunchedEffect(countDownMs) {
        while (countDownMs > 0) {
            delay(1000)
            countDownMs -= 1000
        }
    }


    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
        softKeyBoard?.show()
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .fillMaxWidth()
            .height(52.cdp)
            .clip(shape = RoundedCornerShape(12.cdp))
            .background(Color(0x337f7f7f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.cdp)
                .focusRequester(focusRequester),
            value = curInputStr,
            onValueChange = {
                if (it.length < 5) {
                    curInputStr = it
                    onCaptchaCodeChange(it)
                }
            },
            textStyle = TextStyle(
                fontSize = TextUnit(18.csp.value, TextUnitType.Sp),
                color = Color(0xff161823)
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            cursorBrush = SolidColor(Color(0xfffe2c55))
        )

        if (countDownMs > 0) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = 16.cdp),
                text = "${countDownMs / 1000}秒后重新发送",
                color = Color(0x7fababaf),
                fontSize = 14.csp
            )
        } else {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = 16.cdp)
                    .clickable {
                        countDownMs = countDownMillis
                        curInputStr = ""
                        onCaptchaCodeChange("")
                        onClickResend()
                    },
                text = "重新发送",
                color = Color(0x6604498d),
                fontSize = 14.csp
            )
        }
    }
}

@Preview
@Composable
fun PreviewFindPasswordPage() {
    FindPasswordEditor()
}