package com.muggle.tiktokcopy.business.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.ConfirmButton
import com.muggle.tiktokcopy.ui.component.FindPasswordEditor
import com.muggle.tiktokcopy.ui.component.LoginToolBar
import com.muggle.tiktokcopy.ui.component.PrivacyConfirmWidget
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun FindPasswordPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.cdp)
    ) {
        LoginToolBar(R.drawable.common_left, "")
        Spacer(modifier = Modifier.height(45.cdp))
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "找回密码",
            fontSize = 24.csp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(11.cdp))
        // TODO: 修改手机号码显示逻辑
        Text(
            modifier = Modifier.padding(start = 24.cdp),
            text = "短信已发送至+86 182 1483 9928",
            fontSize = 14.csp,
            color = Color(0x7fababaf),
            style = TextStyle(baselineShift = BaselineShift.Subscript)
        )
        Spacer(modifier = Modifier.height(11.cdp))
        FindPasswordEditor()
        Spacer(modifier = Modifier.height(8.cdp))
        PrivacyConfirmWidget(horizontal = Arrangement.Start)
        Spacer(modifier = Modifier.height(16.cdp))
        ConfirmButton(hintText = "完成")
    }
}

@Preview
@Composable
fun PreviewFindPasswordPage() {
    FindPasswordPage()
}