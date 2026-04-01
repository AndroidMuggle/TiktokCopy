package com.muggle.tiktokcopy.ui.component.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.state.SingleTabUiState
import com.muggle.tiktokcopy.ui.component.video.bean.TabItemState
import com.muggle.tiktokcopy.utils.VerticalDivider
import com.muggle.tiktokcopy.utils.cdp
import com.muggle.tiktokcopy.utils.csp
import kotlinx.coroutines.delay

/**
 * @date 2026/2/8 22:34
 * @author muggle
 * @desc
 */
@Composable
fun ScrollableTabList(
    selectedIndex: Int = 0,
    tabList: SnapshotStateList<SingleTabUiState>,
    lazyListState: LazyListState,
    onSelectTabChange: (Int) -> Unit = {},
    onRefreshTab: (Int) -> Unit = {},
    onLongClickTab: () -> Unit = {}
) {

    val lastVisibleItemIndex by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
    }

    Box(
        modifier = Modifier
            .height(40.cdp)
            .width(288.cdp),
    ) {
        LazyRow(
            modifier = Modifier
                .height(40.cdp)
                .width(288.cdp),
            state = lazyListState
        ) {
            itemsIndexed(
                items = tabList,
                key = { _: Int, item: SingleTabUiState ->
                    when (item.tabItemState) {
                        is TabItemState.NormalTab -> {
                            item.tabName
                        }

                        is TabItemState.Refreshing -> {
                            item.tabName
                        }
                    }
                }
            ) { index: Int, item: SingleTabUiState ->
                TabItem(
                    index = index,
                    isSelected = item.isSelected,
                    state = item,
                    onSelectTabChange = {
                        if (lazyListState.isScrollInProgress) {
                            return@TabItem
                        }
                        onSelectTabChange(it)
                    },
                    onRefreshTab = {
                        if (lazyListState.isScrollInProgress) {
                            return@TabItem
                        }
                        onRefreshTab(it)
                    },
                    onLongClickTab = {
                        if (lazyListState.isScrollInProgress) {
                            return@TabItem
                        }
                        onLongClickTab()
                    }
                )
            }
        }

        if (lastVisibleItemIndex != tabList.lastIndex) {
            Box(
                modifier = Modifier
                    .height(40.cdp)
                    .width(40.cdp)
                    .padding(top = 5.cdp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0x99000000),
                                Color.Black
                            )
                        )
                    )
                    .align(Alignment.TopEnd)
                    .clickable {
                        if (lazyListState.isScrollInProgress) {
                            return@clickable
                        }
                        onSelectTabChange(tabList.lastIndex)
                    }
            ) {
                Image(
                    modifier = Modifier
                        .size(20.cdp)
                        .background(
                            color = Color(0xffffffff),
                            shape = RoundedCornerShape(20.cdp)
                        )
                        .align(Alignment.TopEnd),
                    painter = painterResource(R.drawable.video_common_right_arrow),
                    contentDescription = ""
                )
            }
        }
    }

}

@Composable
private fun TabItem(
    index: Int,
    isSelected: Boolean,
    state: SingleTabUiState,
    onSelectTabChange: (Int) -> Unit = {},
    onRefreshTab: (Int) -> Unit = {},
    onLongClickTab: () -> Unit = {}
) {
    when (state.tabItemState) {
        is TabItemState.Refreshing -> {
            RefreshTabWidget(index)
        }

        is TabItemState.NormalTab -> {
            NormalTabWidget(
                index = index,
                isSelected = isSelected,
                state = state,
                onSelectTabChange = onSelectTabChange,
                onRefreshTab = onRefreshTab,
                onLongClickTab = onLongClickTab
            )
        }
    }
}

@Composable
private fun RefreshTabWidget(index: Int) {
    var rotateDegree by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(200)
            if (rotateDegree >= 360) {
                rotateDegree = 0
            }
            rotateDegree += 45
        }
    }
    Box(
        modifier = Modifier
            .height(40.cdp)
            .padding(horizontal = 7.cdp)
            .wrapContentWidth()
            .background(color = Color.Gray)
    ) {
        Image(
            modifier = Modifier
                .size(16.cdp)
                .align(alignment = Alignment.Center)
                .rotate(rotateDegree.toFloat()),
            painter = painterResource(R.drawable.video_tab_refresh),
            contentDescription = ""
        )
    }
}

@Composable
private fun NormalTabWidget(
    index: Int,
    isSelected: Boolean = false,
    state: SingleTabUiState,
    onSelectTabChange: (Int) -> Unit = {},
    onRefreshTab: (Int) -> Unit = {},
    onLongClickTab: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(40.cdp)
            .wrapContentWidth()
            .combinedClickable(
                onClick = {
                    if (isSelected) {
                        onRefreshTab(index)
                    } else {
                        onSelectTabChange(index)
                    }
                },
                onLongClick = {
                    onLongClickTab()
                }
            )
    ) {
        Column(
            modifier = Modifier
                .height(40.cdp)
                .padding(horizontal = 7.cdp)
                .wrapContentWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = state.tabName,
                color = if (isSelected) {
                    Color.White
                } else {
                    Color(0xffc3c3c3)
                },
                fontSize = 16.csp,
                fontWeight = if (state.isSpecialActivity) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }
            )

            if (isSelected) {
                VerticalDivider(height = 8.cdp)
                Spacer(
                    modifier = Modifier
                        .height(2.cdp)
                        .width(24.cdp)
                        .background(color = Color.White)
                )
            }
        }

        if (state.message.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.TopEnd)
            ) {
                VerticalDivider(4.cdp)
                Text(
                    modifier = Modifier
                        .height(16.cdp)
                        .background(
                            color = Color(0xfffe2c55),
                            shape = RoundedCornerShape(24.cdp)
                        )
                        .padding(horizontal = 3.cdp),
                    text = state.message,
                    fontSize = 10.csp,
                    color = Color.White,
                )
            }

        } else if (state.hasRedDot) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = Alignment.TopEnd)
            ) {
                VerticalDivider(4.cdp)
                Spacer(
                    modifier = Modifier
                        .size(9.cdp)
                        .background(
                            color = Color(0xfffe2c55),
                            shape = RoundedCornerShape(9.cdp)
                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScrollableTabList() {
//    NormalTabWidget(
//        true,
//        state = TabItemState.NormalTab(
//            tabName = "推荐",
//            hasRedDot = false,
//            message = "直播",
//            isSpecialActivity = false
//        ),
//    )
//    RefreshTabWidget()
//    ScrollableTabList(0, getTabItemList())
}
