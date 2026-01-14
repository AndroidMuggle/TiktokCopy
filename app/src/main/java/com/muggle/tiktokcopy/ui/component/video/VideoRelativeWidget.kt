package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.video.bean.VideoRelativeContent
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2026/1/9 0:09
 * @author muggle
 * @desc
 */
@Composable
fun VideoRelativeWidget(state: VideoRelativeContent = VideoRelativeContent.SpecialSelect) {
    val curState by remember {
        mutableStateOf(state)
    }

    when (curState) {
        is VideoRelativeContent.ImageWithDescription -> {
            ImageWithDescriptionWidget()
        }

        is VideoRelativeContent.Location -> {
            LocationWidget(curState as VideoRelativeContent.Location)
        }

        VideoRelativeContent.SpecialSelect -> {
            SpecialSelectWidget()
        }
    }
}

@Composable
private fun SpecialSelectWidget() {
    Row(
        modifier = Modifier
            .width(108.cdp)
            .height(20.cdp)
            .clip(shape = RoundedCornerShape(4.cdp))
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            modifier = Modifier.size(12.cdp),
            painter = painterResource(R.drawable.video_tiktok_special_select),
            contentDescription = ""
        )

        Text(
            modifier = Modifier.wrapContentSize(),
            text = "抖音精选内容",
            fontSize = 12.csp,
            color = Color.Black
        )

        Image(
            modifier = Modifier
                .height(12.cdp)
                .width(10.cdp),
            painter = painterResource(R.drawable.video_common_right_arrow),
            contentDescription = ""
        )
    }
}

@Composable
private fun LocationWidget(
    location: VideoRelativeContent.Location = VideoRelativeContent.Location(
        title = "北京",
        locationName = "天安门",
        subDescriptions = listOf(
            "直播中",
            "20W人想去",
            "本地必玩榜",
            "本地必玩榜",
            "本地必玩榜",
            "本地必玩榜"
        )
    )
) {
    Row(
        modifier = Modifier
            .widthIn(max = 268.cdp)
            .height(20.cdp)
            .clip(shape = RoundedCornerShape(4.cdp))
            .background(color = Color(0xff424242)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Spacer(
            modifier = Modifier
                .height(8.cdp)
                .width(2.cdp)
        )

        Image(
            modifier = Modifier.size(12.cdp),
            painter = painterResource(R.drawable.video_location),
            contentDescription = ""
        )

        Spacer(
            modifier = Modifier
                .height(8.cdp)
                .width(2.cdp)
        )

        Text(
            modifier = Modifier.wrapContentSize(),
            text = location.title,
            fontSize = 12.csp,
            color = Color.White
        )

        if (!location.locationName.isNullOrEmpty()) {
            Spacer(
                modifier = Modifier
                    .height(8.cdp)
                    .width(2.cdp)
            )

            Spacer(
                modifier = Modifier
                    .height(10.cdp)
                    .width(1.cdp)
                    .background(color = Color.Gray)
            )

            Spacer(
                modifier = Modifier
                    .height(8.cdp)
                    .width(2.cdp)
            )

            Text(
                modifier = Modifier.wrapContentSize(),
                text = location.locationName,
                fontSize = 12.csp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(
                modifier = Modifier
                    .height(8.cdp)
                    .width(2.cdp)
            )
        }

        if (!location.subDescriptions.isNullOrEmpty()) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = location.subDescriptions.fold("") { acc, subDescription ->
                    "$acc·$subDescription"
                },
                fontSize = 12.csp,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(
                modifier = Modifier
                    .height(8.cdp)
                    .width(2.cdp)
            )
        }

    }
}

@Composable
private fun ImageWithDescriptionWidget(
    imageWithDescription: VideoRelativeContent.ImageWithDescription = VideoRelativeContent.ImageWithDescription(
        imgUrl = "",
        title = "附近",
        typeName = "肯德基召楼星天地",
        subDescriptions = listOf(
            "直播中",
            "20W人想去",
            "本地必玩榜",
            "本地必玩榜",
            "本地必玩榜",
            "本地必玩榜"
        )
    )
) {
    Row(
        modifier = Modifier
            .widthIn(max = 268.cdp)
            .wrapContentHeight(align = Alignment.Top)
            .clip(shape = RoundedCornerShape(4.cdp))
            .background(color = Color(0xff424242))
            .padding(6.cdp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        AsyncImage(
            modifier = Modifier.size(20.cdp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageWithDescription.imgUrl)
                .build(),
            placeholder = painterResource(R.drawable.common_tiktok_default_icon),
            error = painterResource(R.drawable.common_tiktok_default_icon),
            contentDescription = ""
        )

        Spacer(
            modifier = Modifier
                .width(8.cdp)
        )

        Column(modifier = Modifier.widthIn(max = 224.cdp)) {
            Text(
                modifier = Modifier
                    .widthIn(max = 224.cdp)
                    .wrapContentHeight(align = Alignment.Top),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 14.csp
                        )
                    ) {
                        append(imageWithDescription.title)

                        if (!imageWithDescription.typeName.isNullOrEmpty()) {
                            append(" ")
                            append("|")
                            append(" ")
                            append(
                                if (imageWithDescription.typeName.lastIndex > 5) {
                                    imageWithDescription.typeName.substring(0, 4) + "..."
                                } else {
                                    imageWithDescription.typeName
                                }
                            )
                        }
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Color(0x7fffffff),
                            fontSize = 12.csp
                        )
                    ) {
                        append(imageWithDescription.subDescriptions?.fold("") { acc, subDescription ->
                            "$acc·$subDescription"
                        })
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewVideoRelativeWidget() {
    ImageWithDescriptionWidget()
}