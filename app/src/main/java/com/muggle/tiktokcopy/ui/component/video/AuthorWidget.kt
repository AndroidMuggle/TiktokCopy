package com.muggle.tiktokcopy.ui.component.video

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.video.bean.AuthorVerificationType
import com.muggle.tiktokcopy.ui.component.video.bean.AuthorWidgetType
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp
import java.util.Date

/**
 * @date 2026/1/18 15:16
 * @author muggle
 * @desc
 */
@Composable
fun AuthorWidget(authorWidgetType: AuthorWidgetType) {
    val curType by remember {
        mutableStateOf(authorWidgetType)
    }

    when (curType) {
        AuthorWidgetType.Article -> {
            ArticleWidget()
        }

        is AuthorWidgetType.AuthorVerification -> {
            AuthorVerificationWidget((curType as AuthorWidgetType.AuthorVerification).verificationType)
        }

        is AuthorWidgetType.CreateTogether -> {
            CreateTogetherWidget(curType as AuthorWidgetType.CreateTogether)
        }

        AuthorWidgetType.LivePhoto -> {
            LivePhotoWidget()
        }

        AuthorWidgetType.VideoChapter -> {
            VideoChapterWidget()
        }

        is AuthorWidgetType.VideoCreateDate -> {
            VideoCreateDateWidget(curType as AuthorWidgetType.VideoCreateDate)
        }
    }
}

@Composable
private fun ArticleWidget() {
    Row(
        modifier = Modifier
            .width(50.cdp)
            .height(20.cdp),
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
private fun CreateTogetherWidget(createTogether: AuthorWidgetType.CreateTogether) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(20.cdp),
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
            overflow = TextOverflow.Visible
        )
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
private fun VideoChapterWidget() {
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

@Preview
@Composable
fun PreviewAuthorWidget() {

}