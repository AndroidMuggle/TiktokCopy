package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.video.bean.VideoAlbumState
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2025/12/19 0:13
 * @author muggle
 * @desc
 */
@Composable
fun MusicAlbumEntrance(
    albumState: VideoAlbumState = VideoAlbumState.ListenVideo
) {
    val curState by remember {
        mutableStateOf(albumState)
    }

    Box(
        modifier = Modifier.size(56.cdp),
        contentAlignment = Alignment.TopCenter
    ) {
        when (curState) {
            VideoAlbumState.AlbumImage -> {
                AsyncImage(
                    modifier = Modifier
                        .size(56.cdp)
                        .clip(shape = CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("")
                        .build(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.common_nav_user_avatar_holder),
                    placeholder = painterResource(R.drawable.common_nav_user_avatar_holder),
                    contentDescription = "",
                )
            }

            VideoAlbumState.FilmSameVideo -> {

                AsyncImage(
                    modifier = Modifier
                        .size(56.cdp)
                        .align(alignment = Alignment.TopCenter)
                        .clip(shape = CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("")
                        .build(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.common_checked),
                    placeholder = painterResource(R.drawable.common_checked),
                    contentDescription = "",
                )

                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(alignment = Alignment.BottomCenter),
                    text = "拍同款",
                    fontSize = 14.csp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffffffff)
                )
            }

            VideoAlbumState.ListenVideo -> {

                Spacer(
                    modifier = Modifier
                        .size(56.cdp)
                        .clip(CircleShape)
                        .background(Color(0xff53524f))
                )

                Image(
                    modifier = Modifier
                        .size(42.cdp),
                    painter = painterResource(R.drawable.video_listen_collection),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(alignment = Alignment.BottomCenter)
                        .shadow(elevation = 25.cdp, shape = RectangleShape),
                    text = "听抖音",
                    fontSize = 14.csp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffffffff)
                )
            }

            VideoAlbumState.ListenVideoCollection -> {
                Image(
                    modifier = Modifier
                        .size(42.cdp),
                    painter = painterResource(R.drawable.video_listen_collection),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(alignment = Alignment.BottomCenter)
                        .shadow(elevation = 25.cdp, shape = RectangleShape),
                    text = "听合集",
                    fontSize = 14.csp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffffffff)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMusicAlbumEntrance() {
    MusicAlbumEntrance()
}