package com.muggle.tiktokcopy.business.home.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.home.intent.ScrollTabClickAct
import com.muggle.tiktokcopy.business.home.intent.VideoPlayAct
import com.muggle.tiktokcopy.business.home.intent.VideoWidgetClickAct
import com.muggle.tiktokcopy.business.home.repo.HomeScreenRepo
import com.muggle.tiktokcopy.business.home.state.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * @date 2026/3/22 23:26
 * @author muggle
 * @desc
 */
@HiltViewModel
class HomeScreenVm @Inject constructor(private val repo: HomeScreenRepo) : ViewModel() {
    private val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState

    fun onReceiveScrollTabClickAct(scrollTabClickAct: ScrollTabClickAct) {
        when (scrollTabClickAct) {
            ScrollTabClickAct.LongClickTab -> {
                TODO()
            }

            is ScrollTabClickAct.SelectTab -> {
                _homeScreenUiState.update {
                    it.copy(tabItemList = it.tabItemList.apply {
                        forEachIndexed { index, state ->
                            if (state.isSelected) {
                                set(index, state.copy(isSelected = false))
                            }
                        }
                        set(
                            scrollTabClickAct.index,
                            get(scrollTabClickAct.index).copy(isSelected = true)
                        )
                    })
                }
            }

            is ScrollTabClickAct.RefreshTab -> {
                TODO()
            }
        }
    }
}