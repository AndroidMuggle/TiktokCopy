package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.ui.component.video.bean.LikeState
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2025/12/15 23:18
 * @author muggle
 * @desc 点赞组件
 */
@Composable
fun LikeWidget(
    countString: String = "0",
    likeState: LikeState = LikeState.Unlike,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {

    var curLikeState by remember {
        mutableStateOf(likeState)
    }

    Box(modifier = Modifier.size(56.cdp)) {
        when (curLikeState) {
            LikeState.LikeChecking -> {
                AnimatedVisibility(
                    modifier = Modifier
                        .size(45.cdp)
                        .align(alignment = Alignment.TopCenter),
                    visible = curLikeState is LikeState.LikeChecking,
                    enter = expandIn(
                        animationSpec = tween(
                            durationMillis = 2000,
                            delayMillis = 0,
                            easing = LinearEasing
                        ),
                        expandFrom = Alignment.Center
                    )
                ) {
                    Image(
                        modifier = Modifier
                            .size(45.cdp)
                            .align(alignment = Alignment.TopCenter),
                        painter = painterResource(R.drawable.video_like_check),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }

                Image(
                    modifier = Modifier
                        .size(40.cdp)
                        .align(alignment = Alignment.TopCenter),
                    painter = painterResource(R.drawable.video_like_selected),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }

            LikeState.Liked -> {
                Image(
                    modifier = Modifier
                        .size(40.cdp)
                        .align(alignment = Alignment.TopCenter)
                        .clickable {
                            onClickAct(VideoWidgetClickAct.ClickLikeWidget(false))
                        },
                    painter = painterResource(R.drawable.video_like_selected),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }

            LikeState.Unlike -> {
                Image(
                    modifier = Modifier
                        .size(40.cdp)
                        .align(alignment = Alignment.TopCenter)
                        .clickable {
                            onClickAct(VideoWidgetClickAct.ClickLikeWidget(true))
                        },
                    painter = painterResource(R.drawable.video_like_unselect),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
        }


        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.BottomCenter),
            text = countString,
            fontSize = 14.csp,
            color = Color(0x99ffffff)
        )
    }
}

@Composable
@Preview
fun PreviewLikeWidget() {
    LikeWidget()
}

