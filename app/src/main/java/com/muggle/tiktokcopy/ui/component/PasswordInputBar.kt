package com.muggle.tiktokcopy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun PasswordInputBar() {

    var isPreviewOpen by remember {
        mutableStateOf(false)
    }

    var realInputStr by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .fillMaxWidth()
            .height(52.cdp)
            .clip(shape = RoundedCornerShape(12.cdp))
            .background(Color(0x337f7f7f))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(23.cdp))
            Icon(
                modifier = Modifier
                    .size(18.cdp)
                    .clickable {
                        isPreviewOpen = !isPreviewOpen
                    },
                painter = painterResource(
                    if (isPreviewOpen) {
                        R.drawable.password_preview_open
                    } else {
                        R.drawable.password_preview_close
                    }
                ),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(25.cdp))
            Spacer(
                modifier = Modifier
                    .height(10.cdp)
                    .width(0.67.cdp)
                    .background(Color(0xffababab))
            )
            Spacer(modifier = Modifier.width(7.cdp))
            BasicTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                value = realInputStr,
                onValueChange = { pwd ->
                    // TODO: 密码合法性校验
                    if (pwd.length < 20) {
                        realInputStr = pwd
                    }
                },
                textStyle = TextStyle(
                    fontSize = TextUnit(18.csp.value, TextUnitType.Sp),
                    color = Color(0xff161823)
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                cursorBrush = SolidColor(Color(0xfffe2c55)),
                visualTransformation = if (!isPreviewOpen) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                }
            )
        }

        if (realInputStr.isEmpty()) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(73.33.cdp))
                Text(text = "请输入密码", fontSize = 14.csp, color = Color(0x7fababaf))
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(309.33.cdp))
                Image(
                    modifier = Modifier
                        .size(18.cdp)
                        .clickable {
                            realInputStr = ""
                        },
                    painter = painterResource(R.drawable.login_clear_input),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPasswordInputBar() {
    PasswordInputBar()
}