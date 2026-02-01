package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.ui.component.video.bean.ProgressWidgetType
import com.muggle.tiktokcopy.ui.theme.APP_DESIGN_WIDTH
import com.muggle.tiktokcopy.utils.cdp

/**
 * @date 2026/1/22 23:35
 * @author muggle
 * @desc
 */
@Composable
fun VideoProgressWidget(
    type: ProgressWidgetType = ProgressWidgetType.Playing,
    totalTimeSec: Long = 100L,
    curSec: Long = 50L,
    chapterSecList: List<Long> = listOf()
) {

    val curType by remember {
        mutableStateOf(type)
    }

    val height by remember {
        mutableStateOf(
            when (curType) {
                ProgressWidgetType.Pause -> {
                    2.cdp
                }

                ProgressWidgetType.Playing -> {
                    1.cdp
                }
            }
        )
    }

    val curFactor by remember {
        mutableFloatStateOf(curSec / totalTimeSec.toFloat())
    }

    val chapterFactorList by remember {
        mutableStateOf(
            chapterSecList.map {
                it / totalTimeSec.toFloat()
            }
        )
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 11.cdp),
        contentAlignment = Alignment.CenterStart
    ) {
        Spacer(
            modifier = Modifier
                .height(height = height)
                .fillMaxWidth(1f)
                .background(color = Color(0x7f525252), shape = RoundedCornerShape(height))
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            chapterFactorList.forEach {
                Spacer(
                    modifier = Modifier
                        .height(height = height)
                        .width(1.cdp)
                        .offset(((APP_DESIGN_WIDTH - 22).toFloat() * it).cdp)
                        .background(color = Color(0xffffffff))

                )
            }
        }

        Spacer(
            modifier = Modifier
                .height(height = height)
                .fillMaxWidth(curFactor)
                .background(
                    color = if (curType is ProgressWidgetType.Playing) {
                        Color(0x7fffffff)
                    } else {
                        Color(0xcfffffff)
                    }
                )
        )

        Spacer(
            modifier = Modifier
                .height(height = height + 3.cdp)
                .width(height + 3.cdp)
                .offset(((APP_DESIGN_WIDTH - 22).toFloat() * curFactor).cdp)
                .background(color = Color(0xffffffff), shape = CircleShape)

        )
        rememberLazyListState()
    }
}

@Preview
@Composable
fun PreviewVideoProgressWidget() {
    VideoProgressWidget(chapterSecList = listOf(5, 20, 75, 90))
}