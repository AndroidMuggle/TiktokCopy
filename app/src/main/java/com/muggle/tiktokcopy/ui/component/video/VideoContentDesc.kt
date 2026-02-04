package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2026/2/4 23:03
 * @author muggle
 * @desc
 */
@Composable
fun VideoContentDesc(desc: String = "") {
    // TODO: #类型的话题跳转、直播类型的动效、展开按钮入口
    Text(
        modifier = Modifier.widthIn(max = 276.cdp),
        text = desc,
        color = Color.White,
        fontSize = 15.csp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}