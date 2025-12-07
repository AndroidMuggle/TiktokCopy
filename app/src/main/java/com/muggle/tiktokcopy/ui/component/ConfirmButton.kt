package com.muggle.tiktokcopy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

@Composable
fun ConfirmButton(
    isClickable: Boolean = false,
    hintText: String = "确认",
    onConfirm: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.cdp)
            .fillMaxWidth()
            .height(52.cdp)
            .clip(shape = RoundedCornerShape(12.cdp))
            .background(
                if (isClickable) {
                    Color(0xfffe2c55)
                } else {
                    Color(0xffffb7c5)
                }
            )
            .clickable {
                if (isClickable) {
                    onConfirm()
                } else {
                    // TODO: 不可点击状态提示用户
                }
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = hintText,
            fontSize = 20.csp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun PreviewConfirmButton() {
    ConfirmButton()
}