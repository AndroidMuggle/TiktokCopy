package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.utils.cdp

/**
 * @date 2025/12/23 22:56
 * @author muggle
 * @desc
 */
@Composable
fun DanmakuEditEntrance(onClick: () -> Unit = {}) {
    Image(
        modifier = Modifier
            .size(35.cdp)
            .clickable {
                onClick()
            },
        painter = painterResource(R.drawable.video_danmaku_entrance),
        contentDescription = ""
    )
}

@Preview
@Composable
fun PreviewDanmakuEditEntrance() {
    DanmakuEditEntrance()
}