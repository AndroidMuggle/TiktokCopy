package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.bean.VideoContentWarningWidgetClickType
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.ui.component.video.bean.VideoContentWarningType
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2026/1/18 13:07
 * @author muggle
 * @desc
 */
@Composable
fun VideoContentWarningWidget(
    type: VideoContentWarningType,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {
    val curState by remember {
        mutableStateOf(type)
    }

    when (curState) {
        is VideoContentWarningType.AuthorWarning -> {
            AuthorWarning(curState as VideoContentWarningType.AuthorWarning, onClickAct)
        }

        is VideoContentWarningType.ContentWarning -> {
            ContentWarningWidget(curState as VideoContentWarningType.ContentWarning, onClickAct)
        }
    }
}

@Composable
private fun AuthorWarning(
    authorWarning: VideoContentWarningType.AuthorWarning,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(start = 11.cdp)
            .clickable {
                onClickAct(
                    VideoWidgetClickAct.ClickVideoContentWarningWidget(
                        VideoContentWarningWidgetClickType.AuthorWarning
                    )
                )
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier.size(15.cdp),
            painter = painterResource(R.drawable.video_warning_author),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(6.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = authorWarning.authorWarningStr,
            fontSize = 12.csp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(6.cdp))
        Image(
            modifier = Modifier
                .wrapContentHeight()
                .width(8.cdp),
            painter = painterResource(R.drawable.vidoe_content_right_gray),
            contentDescription = ""
        )
    }
}

@Composable
private fun ContentWarningWidget(
    contentWarning: VideoContentWarningType.ContentWarning,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(start = 11.cdp)
            .clickable {
                onClickAct(
                    VideoWidgetClickAct.ClickVideoContentWarningWidget(
                        VideoContentWarningWidgetClickType.ContentWarning
                    )
                )
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier.size(15.cdp),
            painter = painterResource(R.drawable.video_content_attention),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(6.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = contentWarning.contentWarningStr,
            fontSize = 12.csp,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun PreviewVideoContentWarningWidget(type: VideoContentWarningType) {
}

