package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.unit.IntSize
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.video.bean.CollectState
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp

/**
 * @date 2025/12/17 22:52
 * @author muggle
 * @desc
 */
@Composable
fun AddCollectWidget(
    collectCountString: String = "0",
    collectState: CollectState = CollectState.Idle
) {
    var curCollectState by remember {
        mutableStateOf(collectState)
    }

    Box(
        modifier = Modifier.size(56.cdp)
    ) {
        when (curCollectState) {
            CollectState.CollectChecking -> {
                Image(
                    modifier = Modifier
                        .size(40.cdp)
                        .align(alignment = Alignment.TopCenter)
                        .clickable {
                            curCollectState = CollectState.CollectChecking
                        },
                    painter = painterResource(R.drawable.video_add_to_collect_selected),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
                AnimatedVisibility(
                    modifier = Modifier
                        .size(56.cdp)
                        .align(alignment = Alignment.TopCenter),
                    visible = curCollectState is CollectState.CollectChecking,
                    enter = expandIn(
                        initialSize = {
                            IntSize(
                                40.cdp.value.toInt(),
                                40.cdp.value.toInt()
                            )
                        },
                        expandFrom = Alignment.Center,
                        animationSpec = spring()
                    )
                ) {
                    Image(
                        modifier = Modifier
                            .size(56.cdp)
                            .align(alignment = Alignment.TopCenter),
                        painter = painterResource(R.drawable.video_collect_checking),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }

            }

            CollectState.Collected -> {
                Image(
                    modifier = Modifier
                        .size(40.cdp)
                        .align(alignment = Alignment.TopCenter)
                        .clickable {
                            curCollectState = CollectState.CollectChecking
                        },
                    painter = painterResource(R.drawable.video_add_to_collect_selected),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }

            CollectState.Idle -> {
                Image(
                    modifier = Modifier
                        .size(40.cdp)
                        .align(alignment = Alignment.TopCenter)
                        .clickable {
                            curCollectState = CollectState.CollectChecking
                        },
                    painter = painterResource(R.drawable.video_add_to_collect),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
        }

        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.BottomCenter),
            text = collectCountString,
            fontSize = 14.csp,
            color = Color(0x99ffffff)
        )
    }
}

@Preview
@Composable
fun PreviewAddCollectWidget() {
    AddCollectWidget()
}