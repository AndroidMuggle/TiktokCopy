package com.muggle.tiktokcopy.ui.component.video

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.bean.AuthorWidgetClickType
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.business.login.bean.LoginResponseBean
import com.muggle.tiktokcopy.ui.component.video.bean.AuthorVerificationType
import com.muggle.tiktokcopy.ui.component.video.bean.AuthorWidgetType
import com.muggle.tiktokcopy.utils.HorizontalDivider
import com.muggle.tiktokcopy.utils.VerticalDivider
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp
import java.util.Date

/**
 * @date 2026/1/18 15:16
 * @author muggle
 * @desc
 */
@Composable
fun VideoAuthor(
    userName: String,
    authorWidgetType: AuthorWidgetType? = null,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {
    Column(modifier = Modifier.wrapContentSize()) {
        Row(
            modifier = Modifier
                .widthIn(max = 276.cdp)
                .height(20.cdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .widthIn(max = 276.cdp)
                    .clickable {
                        onClickAct(VideoWidgetClickAct.ClickAuthorName)
                    },
                text = "@$userName",
                fontSize = 15.csp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.cdp))
            if (null != authorWidgetType) {
                AuthorWidget(authorWidgetType)
            }
        }
        Spacer(modifier = Modifier.height(8.cdp))

        if (authorWidgetType is AuthorWidgetType.CreateTogether) {
            CreateTogetherAuthorList(authorList = authorWidgetType.authorList, onClickAct)
        }
    }
}

@Composable
private fun AuthorWidget(
    authorWidgetType: AuthorWidgetType,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {
    val curType by remember {
        mutableStateOf(authorWidgetType)
    }

    when (curType) {
        AuthorWidgetType.Article -> {
            ArticleWidget(onClickAct)
        }

        is AuthorWidgetType.AuthorVerification -> {
            AuthorVerificationWidget((curType as AuthorWidgetType.AuthorVerification).verificationType)
        }

        is AuthorWidgetType.CreateTogether -> {
            CreateTogetherWidget(curType as AuthorWidgetType.CreateTogether, onClickAct)
        }

        AuthorWidgetType.LivePhoto -> {
            LivePhotoWidget()
        }

        AuthorWidgetType.VideoChapter -> {
            VideoChapterWidget(onClickAct)
        }

        is AuthorWidgetType.VideoCreateDate -> {
            VideoCreateDateWidget(curType as AuthorWidgetType.VideoCreateDate)
        }
    }
}

@Composable
private fun ArticleWidget(onClickAct: (VideoWidgetClickAct) -> Unit = {}) {
    Row(
        modifier = Modifier
            .width(50.cdp)
            .height(20.cdp)
            .clickable {
                onClickAct(VideoWidgetClickAct.ClickAuthorWidget(AuthorWidgetClickType.VideoChapterDetailList))
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(5.cdp))
        Image(
            modifier = Modifier
                .width(10.cdp)
                .height(12.cdp)
                .background(color = Color(0xff525252), shape = RoundedCornerShape(4.cdp)),
            painter = painterResource(R.drawable.video_doc_detail),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(5.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "文章",
            color = Color.White,
            fontSize = 12.csp
        )
    }
}


@Composable
private fun CreateTogetherWidget(
    createTogether: AuthorWidgetType.CreateTogether,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(color = Color(0x66666666), shape = RoundedCornerShape(4.cdp))
            .clickable {
                onClickAct(VideoWidgetClickAct.ClickAuthorWidget(AuthorWidgetClickType.CreateTogether))
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(5.cdp))
        Image(
            modifier = Modifier
                .width(10.cdp)
                .height(12.cdp)
                .background(color = Color(0xff525252), shape = RoundedCornerShape(4.cdp)),
            painter = painterResource(R.drawable.video_create_together),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(5.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "${createTogether.authorList.size}人共创",
            color = Color.White,
            fontSize = 12.csp,
            overflow = TextOverflow.Visible,
            textAlign = TextAlign.Center,
        )
        HorizontalDivider(5.cdp)
    }
}

@Composable
private fun LivePhotoWidget() {
    Row(
        modifier = Modifier
            .width(52.cdp)
            .height(20.cdp)
    ) {
        Spacer(modifier = Modifier.width(5.cdp))
        Image(
            modifier = Modifier
                .width(10.cdp)
                .height(12.cdp)
                .background(color = Color(0xff525252), shape = RoundedCornerShape(4.cdp)),
            painter = painterResource(R.drawable.video_live_photo),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(5.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "动图",
            color = Color.White,
            fontSize = 12.csp,
            overflow = TextOverflow.Visible
        )
    }
}

@Composable
private fun VideoChapterWidget(onClickAct: (VideoWidgetClickAct) -> Unit = {}) {
    Row(
        modifier = Modifier
            .width(52.cdp)
            .height(20.cdp)
            .clickable {
                onClickAct(VideoWidgetClickAct.ClickAuthorWidget(AuthorWidgetClickType.VideoChapterDetailList))
            }
    ) {
        Spacer(modifier = Modifier.width(5.cdp))
        Image(
            modifier = Modifier
                .width(10.cdp)
                .height(12.cdp)
                .background(color = Color(0xff525252), shape = RoundedCornerShape(4.cdp)),
            painter = painterResource(R.drawable.video_live_photo),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(5.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "章节要点",
            color = Color.White,
            fontSize = 12.csp,
            overflow = TextOverflow.Visible
        )
        Spacer(modifier = Modifier.width(5.cdp))
        Spacer(
            modifier = Modifier
                .width(1.cdp)
                .height(12.cdp)
                .background(Color(0xff7f7f7f))
        )
        Spacer(modifier = Modifier.width(5.cdp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "章节要点",
            color = Color.White,
            fontSize = 12.csp,
            overflow = TextOverflow.Visible
        )
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
private fun VideoCreateDateWidget(videoCreateDate: AuthorWidgetType.VideoCreateDate) {
    val date = Date(videoCreateDate.timeStamp.toLong())
    val sdf = SimpleDateFormat("·yyyy年MM月dd日")
    val formattedDate: String? = sdf.format(date)

    Text(
        modifier = Modifier.wrapContentSize(),
        text = "$formattedDate",
        color = Color.White,
        fontSize = 12.csp,
        overflow = TextOverflow.Visible
    )
}

@Composable
private fun AuthorVerificationWidget(authorVerificationType: AuthorVerificationType) {
    val resId = when (authorVerificationType) {
        AuthorVerificationType.CompanyVerified -> {
            R.drawable.video_author_verification_gov
        }

        AuthorVerificationType.FamousPersonVerified -> {
            R.drawable.video_author_verification_famous_people
        }

        AuthorVerificationType.Government -> {
            R.drawable.video_author_verification_company
        }
    }

    Image(
        modifier = Modifier.size(16.cdp),
        painter = painterResource(resId),
        contentDescription = ""
    )
}

@Composable
private fun CreateTogetherAuthorList(
    authorList: List<LoginResponseBean>,
    onClickAct: (VideoWidgetClickAct) -> Unit = {}
) {
    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.Top
    ) {
        if (authorList.size <= 3) {
            authorList.forEach {
                SingleAuthor(it, onClickAct)
                HorizontalDivider(3.cdp)
            }
        } else {
            authorList.take(3).forEach {
                SingleAuthor(it, onClickAct)
                HorizontalDivider(3.cdp)
            }
            HorizontalDivider(8.cdp)
            MoreAuthor(onClickAct)
        }
    }
}

@Composable
private fun SingleAuthor(
    author: LoginResponseBean,
    onClickAvatar: (VideoWidgetClickAct) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .width(42.cdp)
            .wrapContentHeight()
            .clickable {
                onClickAvatar(VideoWidgetClickAct.ClickCreateTogetherAuthorAvatar(author))
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(author.avatar).build(),
            modifier = Modifier
                .size(30.cdp)
                .clip(
                    shape = RoundedCornerShape(30.cdp)
                )
                .border(width = 1.cdp, color = Color.White),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.common_nav_user_avatar_holder),
            placeholder = painterResource(R.drawable.common_nav_user_avatar_holder)
        )
        VerticalDivider(3.cdp)
        Text(
            modifier = Modifier
                .width(42.cdp)
                .wrapContentHeight(),
            text = author.username ?: "",
            color = Color.White,
            fontSize = 10.csp,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun MoreAuthor(
    onClickAvatar: (VideoWidgetClickAct) -> Unit = {}
) {
    Image(
        modifier = Modifier
            .size(30.cdp)
            .clip(shape = RoundedCornerShape(30.cdp))
            .background(color = Color(0x10FFFFFF))
            .clickable {
                onClickAvatar(VideoWidgetClickAct.ClickCreateTogetherMore)
            },
        painter = painterResource(R.drawable.video_author_more),
        contentScale = ContentScale.Crop,
        contentDescription = ""
    )
}

@Preview
@Composable
fun PreviewAuthorWidget() {
    MoreAuthor()
}