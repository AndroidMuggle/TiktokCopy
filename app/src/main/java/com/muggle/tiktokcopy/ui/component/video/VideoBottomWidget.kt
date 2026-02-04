package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.video.bean.VideoBottomWidgetType
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2026/1/19 22:56
 * @author muggle
 * @desc
 */
@Composable
fun VideoBottomWidget(bottomWidgetType: VideoBottomWidgetType) {
    val curType by remember {
        mutableStateOf(bottomWidgetType)
    }

    when (curType) {
        is VideoBottomWidgetType.ListenMusic -> {
            ListenMusicWidget(curType as VideoBottomWidgetType.ListenMusic)
        }

        is VideoBottomWidgetType.RelativeSearch -> {
            RelativeSearchWidget(curType as VideoBottomWidgetType.RelativeSearch)
        }

        is VideoBottomWidgetType.VideoCollection -> {
            VideoCollectionWidget(curType as VideoBottomWidgetType.VideoCollection)
        }
    }

}

@Composable
private fun ListenMusicWidget(listenMusic: VideoBottomWidgetType.ListenMusic) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .width(65.cdp)
                .height(20.cdp)
                .background(
                    brush = horizontalGradient(
                        listOf(
                            Color(0xff5d7163),
                            Color.Gray
                        ),
                        startX = 0f,
                        endX = 65.cdp.value
                    ),
                    shape = RoundedCornerShape(4.cdp)
                )
                .padding(4.cdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "去汽水听",
                fontSize = 12.csp,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(4.cdp))
            Image(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(8.cdp),
                painter = painterResource(R.drawable.vidoe_content_right_gray),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.width(8.cdp))

        Text(
            modifier = Modifier
                .wrapContentSize()
                .widthIn(max = 210.cdp),
            text = "${listenMusic.musicName}-${listenMusic.musicAuthor}",
            fontSize = 14.csp,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun RelativeSearchWidget(relativeSearch: VideoBottomWidgetType.RelativeSearch) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.cdp)
            .background(color = Color(0x66666666))
            .padding(horizontal = 15.cdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(16.cdp),
                painter = painterResource(R.drawable.video_search),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(6.cdp))
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .widthIn(max = 210.cdp),
                text = "相关搜索·${relativeSearch.searchHintStr}",
                fontSize = 14.csp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis
            )
        }
        Image(
            modifier = Modifier.size(16.cdp),
            painter = painterResource(R.drawable.common_icon_right),
            contentDescription = ""
        )
    }
}

@Composable
private fun VideoCollectionWidget(videoCollection: VideoBottomWidgetType.VideoCollection) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.cdp)
            .background(color = Color(0xff111111))
            .padding(horizontal = 15.cdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(16.cdp),
                painter = painterResource(R.drawable.video_collection_layers),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(6.cdp))
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .widthIn(max = 210.cdp),
                text = "合集·${videoCollection.collectionName}",
                fontSize = 14.csp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis
            )
        }
        Image(
            modifier = Modifier.size(16.cdp),
            painter = painterResource(R.drawable.common_icon_right),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun PreviewVideoBottomWidget() {
    RelativeSearchWidget(VideoBottomWidgetType.RelativeSearch("柚子"))
}