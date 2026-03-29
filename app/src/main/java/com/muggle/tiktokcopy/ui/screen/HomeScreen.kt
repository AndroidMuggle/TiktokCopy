package com.muggle.tiktokcopy.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavHostController
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.business.home.intent.ScrollTabClickAct
import com.muggle.tiktokcopy.business.home.state.HomeScreenState
import com.muggle.tiktokcopy.business.home.state.SingleTabState
import com.muggle.tiktokcopy.business.home.vm.HomeScreenVm
import com.muggle.tiktokcopy.ui.component.video.ScrollableTabList
import com.muggle.tiktokcopy.ui.component.video.VideoPlayerWidget
import com.muggle.tiktokcopy.ui.component.video.bean.TabItemState
import com.muggle.tiktokcopy.ui.screen.bean.HomeScreenTabType
import com.muggle.tiktokcopy.utils.HorizontalDivider
import com.muggle.tiktokcopy.utils.cdp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

/**
 * @date 2026/2/6 0:16
 * @author muggle
 * @desc
 */
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeScreenVm: HomeScreenVm = hiltViewModel()
) {
    val homeScreenState by homeScreenVm.homeScreenState.collectAsStateWithLifecycle()

    HomeScreen(
        homeScreenState,
        onSelectTabChange = {
            homeScreenVm.onReceiveScrollTabClickAct(ScrollTabClickAct.SelectTab(it))
        },
        onRefreshTab = {
            homeScreenVm.onReceiveScrollTabClickAct(ScrollTabClickAct.RefreshTab(it))
        },
        onLongClickTab = {
            homeScreenVm.onReceiveScrollTabClickAct(ScrollTabClickAct.LongClickTab)
        }
    )
}

@Composable
internal fun HomeScreen(
    homeScreenState: HomeScreenState,
    onSelectTabChange: (Int) -> Unit = {},
    onRefreshTab: (Int) -> Unit = {},
    onLongClickTab: () -> Unit = {}
) {
    val curState by remember {
        mutableStateOf(homeScreenState)
    }

    val horPageCount by remember {
        derivedStateOf {
            curState.tabItemList.size
        }
    }

    val horPageState = rememberPagerState(horPageCount - 1) { horPageCount }

    val tabListState = rememberLazyListState(initialFirstVisibleItemIndex = horPageCount - 1)

    val scope = rememberCoroutineScope { Dispatchers.Main.immediate }

    LaunchedEffect(horPageState) {
        snapshotFlow {
            horPageState.settledPage
        }.debounce(500).collect {
            onSelectTabChange(it)

            if (it in 0 until 3) {
                tabListState.animateScrollToItem(0)
            }

            if (it in horPageCount - 3 until horPageCount) {
                tabListState.animateScrollToItem(horPageCount - 1)
            }

            val visibleItemsInfo = tabListState.layoutInfo.visibleItemsInfo
            val middlePixels =
                (tabListState.layoutInfo.viewportEndOffset + tabListState.layoutInfo.viewportStartOffset) / 2
            visibleItemsInfo.forEachIndexed { index, info ->
                if (it == info.index && index in 0 until visibleItemsInfo.size - 1) {
                    val offsetPixels =
                        (info.offset + visibleItemsInfo[index + 1].offset) / 2 - middlePixels
                    Log.i(
                        "TAG",
                        "zzzz HomeScreen: info.offset = ${info.offset}," +
                                "next.offset = ${visibleItemsInfo[index + 1].offset}," +
                                "middlePixels = $middlePixels"
                    )
                    tabListState.animateScrollBy(offsetPixels.toFloat())
                }
            }

        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier
                .height(760.cdp)
                .fillMaxWidth(),
            state = horPageState
        ) {
            PagerContent()
        }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(top = 58.cdp)
                .align(alignment = Alignment.TopCenter),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            Image(
                modifier = Modifier
                    .height(27.cdp)
                    .padding(top = 4.cdp)
                    .wrapContentWidth(),
                painter = painterResource(R.drawable.video_more_menu),
                contentDescription = ""
            )
            HorizontalDivider(10.cdp)
            ScrollableTabList(
                selectedIndex = horPageState.currentPage,
                tabList = curState.tabItemList,
                lazyListState = tabListState,
                onSelectTabChange = {
                    scope.launch {
                        onSelectTabChange(it)
                        horPageState.animateScrollToPage(it)
                    }
                },
                onRefreshTab = onRefreshTab,
                onLongClickTab = onLongClickTab
            )
            HorizontalDivider(10.cdp)
            Image(
                modifier = Modifier
                    .height(27.cdp)
                    .padding(top = 4.cdp)
                    .wrapContentWidth(),
                painter = painterResource(R.drawable.video_search),
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun PagerContent(
    tabType: HomeScreenTabType = HomeScreenTabType.RecommendTab
) {

    when (tabType) {
        HomeScreenTabType.ExperienceTab -> {
        }

        HomeScreenTabType.GroupCouponTab -> {

        }

        HomeScreenTabType.HotNewsTab -> {

        }

        HomeScreenTabType.LiveStreamTab -> {

        }

        HomeScreenTabType.LocalVideoTab -> {

        }

        HomeScreenTabType.RecommendTab -> {
            RecommendTab()
        }

        HomeScreenTabType.ShopMarketTab -> {


        }

        HomeScreenTabType.SpecialActivityTab -> {


        }

        HomeScreenTabType.SpecialSelectTab -> {


        }

        HomeScreenTabType.SubscribedTab -> {


        }
    }
}

/**
 * 推荐tab
 */
@Composable
private fun RecommendTab() {
    val pageCount by remember {
        derivedStateOf {
            5
        }
    }
    val pagerState = rememberPagerState(0) { pageCount }
    VerticalPager(
        modifier = Modifier
            .height(760.cdp)
            .fillMaxWidth()
            .background(color = Color.Red),
        state = pagerState
    ) {
        VideoPlayerWidget(
            player = ExoPlayer.Builder(LocalContext.current).build(),
            contentScale = ContentScale.FillWidth
        )
    }
}

/**
 * 商城tab
 */
@Composable
private fun PagerScope.ShopMarketTab() {

}

/**
 * 已关注tab
 */
@Composable
private fun PagerScope.SubscribedTab() {

}

/**
 * 热点新闻
 */
@Composable
private fun PagerScope.HotNewsTab() {

}

/**
 * 直播
 */
@Composable
private fun PagerScope.LiveStreamTab() {

}

/**
 * 特殊活动
 */
@Composable
private fun PagerScope.SpecialActivityTab() {

}

/**
 * 精选tab
 */
@Composable
private fun PagerScope.SpecialSelectTab() {

}

/**
 * 经验tab
 */
@Composable
private fun PagerScope.ExperienceTab() {

}

/**
 * 本地视频tab
 */
@Composable
private fun PagerScope.LocalVideoTab() {

}

/**
 * 团购tab
 */
@Composable
private fun PagerScope.GroupCouponTab() {

}

fun getTabItemList(): SnapshotStateList<SingleTabState> {
    return SnapshotStateList<SingleTabState>().apply {
        add(
            SingleTabState(
                tabName = "团购",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )
        add(
            SingleTabState(
                tabName = "宿松",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )
        add(
            SingleTabState(
                tabName = "经验",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )

        add(
            SingleTabState(
                tabName = "精选",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )

        add(
            SingleTabState(
                tabName = "集福气",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )

        add(
            SingleTabState(
                tabName = "直播",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )

        add(
            SingleTabState(
                tabName = "热点",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )

        add(
            SingleTabState(
                tabName = "关注",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )

        add(
            SingleTabState(
                tabName = "商城",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = false,
                tabItemState = TabItemState.NormalTab
            )
        )

        add(
            SingleTabState(
                tabName = "推荐",
                hasRedDot = false,
                message = "",
                isSpecialActivity = false,
                isSelected = true,
                tabItemState = TabItemState.NormalTab
            )
        )
    }
}
