package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.bean.RecommendEntranceClickType
import com.muggle.tiktokcopy.business.home.bean.SelfRecommendClickType
import com.muggle.tiktokcopy.ui.component.video.bean.RecommendState
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2025/12/24 0:10
 * @author muggle
 * @desc
 */
@Composable
fun RecommendWidget(
    state: RecommendState = RecommendState.RecommendCount(0),
    onClick: (RecommendEntranceClickType) -> Unit = {}
) {
    var curState by remember {
        mutableStateOf(state)
    }

    when (curState) {
        is RecommendState.RecommendCount -> {
            RecommendCountWidget(curState as RecommendState.RecommendCount, onClick)
        }

        is RecommendState.OtherUserRecommend -> {
            OtherUserRecommendWidget(curState as RecommendState.OtherUserRecommend)
        }

        is RecommendState.SelfRecommend -> {
            SelfRecommendWidget(curState as RecommendState.SelfRecommend)
        }
    }
}

/**
 * 推荐人数
 */
@Composable
private fun RecommendCountWidget(
    recommendState: RecommendState.RecommendCount,
    onClick: (RecommendEntranceClickType) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(28.cdp)
            .background(
                color = Color(0x66666666),
                shape = RoundedCornerShape(size = 4.cdp)
            )
            .clickable {
                onClick(RecommendEntranceClickType.RecommendCount)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(8.cdp))
        Image(
            modifier = Modifier.size(15.cdp),
            painter = painterResource(R.drawable.video_thums_up),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(4.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = if (recommendState.count != 0) {
                "共${recommendState.count}人推荐"
            } else {
                "点击推荐"
            },
            fontSize = 14.csp,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(4.cdp))
        Image(
            modifier = Modifier
                .size(8.cdp, 15.cdp),
            painter = painterResource(R.drawable.common_icon_right),
            contentScale = ContentScale.Fit,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(8.cdp))
    }
}

/**
 * 其他用户推荐
 */
@Composable
private fun OtherUserRecommendWidget(
    recommendState: RecommendState.OtherUserRecommend,
    onClick: (RecommendEntranceClickType) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(28.cdp)
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            modifier = Modifier
                .height(28.cdp)
                .wrapContentWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 4.cdp)
                )
                .clickable {
                    onClick(RecommendEntranceClickType.OtherRecommend)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(4.cdp))
            AsyncImage(
                modifier = Modifier
                    .size(20.cdp)
                    .clip(CircleShape),
                model = recommendState.userAvatar,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.common_nav_user_avatar_holder),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(4.cdp))
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "${recommendState.userName} 推荐",
                fontWeight = FontWeight.Bold,
                fontSize = 14.csp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(4.cdp))
        }
        Spacer(modifier = Modifier.width(4.cdp))

        Image(
            modifier = Modifier
                .size(28.cdp)
                .background(
                    color = Color(0x993B3B3B),
                    shape = RoundedCornerShape(4.cdp)
                )
                .padding(4.cdp),
            painter = painterResource(R.drawable.video_thums_up),
            contentDescription = ""
        )
    }
}

/**
 * 自己推荐
 */
@Composable
private fun SelfRecommendWidget(
    recommendState: RecommendState.SelfRecommend,
    onClick: (RecommendEntranceClickType) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(28.cdp)
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            modifier = Modifier
                .height(28.cdp)
                .wrapContentWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 4.cdp)
                )
                .clickable {
                    onClick(
                        RecommendEntranceClickType.SelfRecommend(
                            SelfRecommendClickType.UserAvatarBoard
                        )
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(4.cdp))
            AsyncImage(
                modifier = Modifier
                    .size(20.cdp)
                    .clip(CircleShape),
                model = recommendState.userAvatar,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.common_nav_user_avatar_holder),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(4.cdp))
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "${recommendState.userName} 推荐",
                fontWeight = FontWeight.Bold,
                fontSize = 14.csp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(4.cdp))
        }
        Spacer(modifier = Modifier.width(4.cdp))

        Image(
            modifier = Modifier
                .size(28.cdp)
                .background(
                    color = Color(0x993B3B3B),
                    shape = RoundedCornerShape(4.cdp)
                )
                .padding(4.cdp)
                .clickable {
                    onClick(
                        RecommendEntranceClickType.SelfRecommend(
                            SelfRecommendClickType.EditEntrance
                        )
                    )
                },
            painter = painterResource(R.drawable.home_recommend_edit),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun PreviewRecommendWidget() {
    OtherUserRecommendWidget(RecommendState.OtherUserRecommend("zzzzz", ""))
}