package com.muggle.tiktokcopy.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * 登录顶部状态栏
 * @param resId 资源id
 * @param hintText 提示文案
 */
@Composable
fun LoginToolBar(
    @DrawableRes resId: Int,
    hintText: String = "",
    onClickBack: () -> Unit = {},
    onClickHelp: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.cdp)
            .padding(horizontal = 24.cdp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(20.cdp)
                .clickable {
                    onClickBack()
                },
            painter = painterResource(resId),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )

        if (hintText.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        onClickHelp()
                    },
                fontSize = 14.csp,
                text = hintText,
                color = Color(0xff000000),
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Preview()
@Composable
fun PreviewLoginToolBar() {
    LoginToolBar(resId = R.drawable.login_down_arrow, hintText = "帮助")
}