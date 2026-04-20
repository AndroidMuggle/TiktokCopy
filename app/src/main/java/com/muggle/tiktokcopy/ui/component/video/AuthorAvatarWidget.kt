package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.bean.VideoAuthorAvatarClickType
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.ui.component.video.bean.SubscribeState
import com.muggle.tiktokcopy.utils.cdp

/**
 * @date 2025/12/10 22:20
 * @author muggle
 * @desc 视频作者icon
 */
@Composable
fun AuthorAvatarWidget(
    avatarUrl: String = "",
    subscribeState: SubscribeState = SubscribeState.Unsubscribe,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {

    var curSubscribeState by remember {
        mutableStateOf(subscribeState)
    }

    Box(
        modifier = Modifier.size(width = 56.cdp, height = 62.cdp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(56.cdp)
                .clip(shape = CircleShape)
                .border(width = 2.cdp, color = Color.White, shape = CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarUrl)
                .build(),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.common_nav_user_avatar_holder),
            placeholder = painterResource(R.drawable.common_nav_user_avatar_holder),
            contentDescription = "",
        )


        when (curSubscribeState) {
            SubscribeState.MutualFollowed -> {
                AnimatedVisibility(
                    modifier = Modifier.align(alignment = Alignment.BottomCenter),
                    visible = curSubscribeState is SubscribeState.MutualFollowed,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 400,
                            delayMillis = 200,
                            easing = LinearEasing
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 400,
                            delayMillis = 0,
                            easing = LinearEasing
                        )
                    ),
                ) {
                    Image(
                        modifier = Modifier
                            .size(20.cdp)
                            .clip(CircleShape)
                            .background(color = Color.White)
                            .padding(3.cdp)
                            .clickable {
                                onClickAct(
                                    VideoWidgetClickAct.ClickAuthorAvatar(
                                        VideoAuthorAvatarClickType.SendMessage
                                    )
                                )
                            }
                            .align(alignment = Alignment.BottomCenter),
                        painter = painterResource(R.drawable.video_send_message),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }
            }

            SubscribeState.Subscribed -> {
                AnimatedVisibility(
                    modifier = Modifier.align(alignment = Alignment.BottomCenter),
                    visible = curSubscribeState is SubscribeState.Subscribed,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 400,
                            delayMillis = 200,
                            easing = LinearEasing
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 400,
                            delayMillis = 0,
                            easing = LinearEasing
                        )
                    ),
                ) {

                }
            }

            SubscribeState.Unsubscribe -> {
                AnimatedVisibility(
                    modifier = Modifier.align(alignment = Alignment.BottomCenter),
                    visible = curSubscribeState is SubscribeState.Unsubscribe,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 400,
                            delayMillis = 200,
                            easing = LinearEasing
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 400,
                            delayMillis = 0,
                            easing = LinearEasing
                        )
                    ),
                ) {
                    Image(
                        modifier = Modifier
                            .size(20.cdp)
                            .align(alignment = Alignment.BottomCenter)
                            .clickable {
                                onClickAct(
                                    VideoWidgetClickAct.ClickAuthorAvatar(
                                        VideoAuthorAvatarClickType.Subscribe
                                    )
                                )
                            },
                        painter = painterResource(R.drawable.video_subscribe_author),
                        contentDescription = ""
                    )
                }
            }

            SubscribeState.SubscribeChecked -> {
                AnimatedVisibility(
                    modifier = Modifier.align(alignment = Alignment.BottomCenter),
                    visible = curSubscribeState is SubscribeState.SubscribeChecked,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 400,
                            delayMillis = 400,
                            easing = LinearEasing
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 400,
                            delayMillis = 0,
                            easing = LinearEasing
                        )
                    ),
                ) {
                    Image(
                        modifier = Modifier
                            .size(20.cdp)
                            .background(color = Color.White, shape = CircleShape)
                            .align(alignment = Alignment.BottomCenter)
                            .clickable {
                                onClickAct(
                                    VideoWidgetClickAct.ClickAuthorAvatar(
                                        VideoAuthorAvatarClickType.AuthorDetail
                                    )
                                )
                            },
                        painter = painterResource(R.drawable.video_subscribe_check),
                        contentDescription = ""
                    )
                }
            }
        }

    }

}

@Preview
@Composable
fun PreviewAuthorAvatar() {
    AuthorAvatarWidget()
}