package com.muggle.tiktokcopy.ui.component.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun PrivacyConfirmWidget(
    isSelected: Boolean = false,
    horizontal: Arrangement.Horizontal = Arrangement.Center,
    onClickPrivacyAct: (Boolean) -> Unit = {}
) {
    var isCheckedPrivacy by remember {
        mutableStateOf(isSelected)
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontal
    ) {
        Image(
            modifier = Modifier
                .size(14.cdp)
                .alpha(
                    if (isCheckedPrivacy) {
                        1f
                    } else {
                        0.5f
                    }
                )
                .clickable {
                    isCheckedPrivacy = !isCheckedPrivacy
                    onClickPrivacyAct(isCheckedPrivacy)
                },
            painter = painterResource(
                if (isCheckedPrivacy) {
                    R.drawable.common_checked
                } else {
                    R.drawable.common_unchecked
                }
            ),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.width(4.cdp))

        // TODO: 富文本和跳转隐私协议
        Text(
            text = "已阅读并同意用户协议和隐私政策",
            fontSize = 14.csp,
            color = Color(0x7fababaf)
        )
    }
}

@Preview
@Composable
fun PreviewConfirmWidget() {
    PrivacyConfirmWidget()
}