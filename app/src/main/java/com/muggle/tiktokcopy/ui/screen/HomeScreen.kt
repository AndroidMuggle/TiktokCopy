package com.muggle.tiktokcopy.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.media3.exoplayer.ExoPlayer
import com.muggle.tiktokcopy.R
import com.muggle.tiktokcopy.ui.component.video.ScrollableTabList
import com.muggle.tiktokcopy.ui.component.video.VideoPlayerWidget
import com.muggle.tiktokcopy.ui.component.video.bean.HomeScreenTabType
import com.muggle.tiktokcopy.ui.component.video.bean.TabItemState
import com.muggle.tiktokcopy.utils.HorizontalDivider
import com.muggle.tiktokcopy.utils.cdp

/**
 * @date 2026/2/6 0:16
 * @author muggle
 * @desc
 */
@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        val horPageCount by remember {
            derivedStateOf {
                getTabItemList().size
            }
        }

        val horPageState = rememberPagerState(0) { horPageCount }

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
                    .height(32.cdp)
                    .wrapContentWidth(),
                painter = painterResource(R.drawable.video_more_menu),
                contentDescription = ""
            )
            HorizontalDivider(4.cdp)
            ScrollableTabList(0, getTabItemList())
            HorizontalDivider(4.cdp)
            Image(
                modifier = Modifier
                    .height(32.cdp)
                    .wrapContentWidth(),
                painter = painterResource(R.drawable.video_search),
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun PagerScope.PagerContent(
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
private fun PagerScope.RecommendTab() {
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

fun getTabItemList(): List<TabItemState> {
    return mutableListOf(
        TabItemState.NormalTab("团购", false, "", false),
        TabItemState.NormalTab("宿松", false, "", false),
        TabItemState.NormalTab("经验", false, "", false),
        TabItemState.NormalTab("精选", false, "", false),
        TabItemState.NormalTab("集福气", false, "", false),
        TabItemState.NormalTab("直播", false, "", false),
        TabItemState.NormalTab("热点", false, "", false),
        TabItemState.NormalTab("关注", false, "", false),
        TabItemState.NormalTab("商城", false, "", false),
        TabItemState.NormalTab("推荐", false, "", false),
    )
}
