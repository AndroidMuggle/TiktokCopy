package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2025/12/18 22:38
 * @author muggle
 * @desc
 */
@Composable
fun ShareWidget(
    shareCountString: String = "0"
) {
    Column(
        modifier = Modifier.size(56.cdp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.size(38.cdp),
            contentScale = ContentScale.Fit,
            painter = painterResource(R.drawable.video_share),
            contentDescription = ""
        )

        Text(
            modifier = Modifier
                .wrapContentSize(),
            text = shareCountString,
            fontSize = 14.csp,
            color = Color(0x99ffffff)
        )
    }
}

@Preview
@Composable
fun PreviewShareWidget() {
    ShareWidget()
}