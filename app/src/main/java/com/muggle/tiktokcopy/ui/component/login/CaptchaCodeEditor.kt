package com.muggle.tiktokcopy.ui.component.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp
import kotlinx.coroutines.delay

/**
 * 验证码输入框
 */
@Composable
fun CaptchaCodeEditor(
    inputCode: String = "",
    onCaptchaCodeChange: (String) -> Unit = {}
) {
    var curCaptchaCode by remember {
        mutableStateOf(inputCode)
    }

    val focusRequester = remember {
        FocusRequester()
    }

    val softKeyBoard = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
        softKeyBoard?.show()
    }

    BasicTextField(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .fillMaxWidth()
            .height(65.cdp)
            .focusRequester(focusRequester),
        value = curCaptchaCode,
        onValueChange = { code ->
            // TODO: 验证码输入类型校验
            if (code.length <= 4) {
                curCaptchaCode = code
                onCaptchaCodeChange(code)
            }
        },
        singleLine = true,
        textStyle = TextStyle(
            color = Color(0xff161823),
            fontSize = 20.csp,
            fontWeight = FontWeight.Bold
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        cursorBrush = SolidColor(Color(0xfffe2c55)),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(4) {
                    val curChar = if (it in 0..curCaptchaCode.lastIndex) {
                        curCaptchaCode[it].toString()
                    } else {
                        ""
                    }
                    CodeBox(curChar, it == curCaptchaCode.lastIndex + 1)
                }
            }
        }
    )
}

@Composable
fun CodeBox(singleCode: String, isShowCursor: Boolean) {

    var cursorVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        while (true) {
            cursorVisible = !cursorVisible
            delay(500)
        }
    }

    Row(
        modifier = Modifier
            .size(65.cdp)
            .clip(shape = RoundedCornerShape(10.cdp))
            .background(Color(0x337f7f7f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = singleCode,
            fontSize = 20.csp,
            color = Color(0xff161823)
        )
        if (isShowCursor && cursorVisible) {
            Spacer(
                modifier = Modifier
                    .width(3.cdp)
                    .height(20.cdp)
                    .background(Color(0xfffe2c55))
            )
        }
    }
}

@Preview
@Composable
fun PreviewCaptchaCodeEditor() {
    CaptchaCodeEditor()
}