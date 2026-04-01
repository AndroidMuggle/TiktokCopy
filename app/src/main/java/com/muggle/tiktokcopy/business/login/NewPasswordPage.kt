package com.muggle.tiktokcopy.business.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.login.intent.NewPasswordEvent
import com.muggle.tiktokcopy.business.login.vm.NewPasswordVm
import com.muggle.tiktokcopy.ui.component.login.ConfirmButton
import com.muggle.tiktokcopy.ui.component.login.LoginToolBar
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun NewPasswordPage(
    newPasswordVm: NewPasswordVm = hiltViewModel(),
) {

    val curState by remember {
        newPasswordVm.newPasswordUiState
    }

    val isConfirmEnable by remember {
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
            onClickBack = {
                newPasswordVm.onReceiveEvent(NewPasswordEvent.ClickBackBtn)
            }
        )
        Spacer(modifier = Modifier.height(45.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "请输入新登录密码",
            fontSize = 24.csp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(11.cdp))
        // TODO: 修改手机号码显示逻辑
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "密码为8-20位，至少包含字母，数字，符号2种组合",
            fontSize = 14.csp,
            color = Color(0x7fababaf),
            style = TextStyle(baselineShift = BaselineShift.Subscript)
        )
        Spacer(modifier = Modifier.height(10.cdp))

        Box(
            modifier = Modifier
                .padding(horizontal = 24.cdp)
                .fillMaxWidth()
                .height(52.cdp)
                .clip(shape = RoundedCornerShape(12.cdp))
                .background(Color(0x337f7f7f))
        ) {
            BasicTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterStart)
                    .padding(16.cdp),
                value = curState.newPassword,
                onValueChange = { pwd ->
                    // TODO: 密码合法性校验
                    if (pwd.length < 20) {
                        newPasswordVm.onReceiveEvent(NewPasswordEvent.InputPassword(pwd))
                    }
                },
                textStyle = TextStyle(
                    fontSize = TextUnit(18.csp.value, TextUnitType.Sp),
                    color = Color(0xff161823)
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                cursorBrush = SolidColor(Color(0xfffe2c55)),
                visualTransformation = PasswordVisualTransformation()
            )

            if (curState.newPassword.isEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(16.cdp),
                    text = "请输入密码",
                    fontSize = 14.csp,
                    color = Color(0x7fababaf)
                )
            }
        }
        Spacer(modifier = Modifier.height(14.cdp))
        ConfirmButton(
            isClickable = isConfirmEnable,
            hintText = "完成",
            onConfirm = {
                newPasswordVm.onReceiveEvent(NewPasswordEvent.ClickConfirmBtn)
            }
        )
    }
}

@Preview
@Composable
fun PreviewNewPasswordPage() {
    NewPasswordPage()
}

