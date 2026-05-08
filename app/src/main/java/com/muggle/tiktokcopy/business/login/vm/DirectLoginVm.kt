package com.muggle.tiktokcopy.business.login.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.login.intent.LoginDirectEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.DirectLoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DirectLoginVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {

    private val _directLoginUiState = MutableStateFlow(DirectLoginUiState())
    val directLoginUiState: StateFlow<DirectLoginUiState> = _directLoginUiState

    fun onReceiveEvent(event: LoginDirectEvent) {
        when (event) {
            is LoginDirectEvent.ClickBackBtn -> {
                TODO("点击返回按钮跳转")
            }

            is LoginDirectEvent.ClickConfirmBtn -> {
                TODO()
            }

            is LoginDirectEvent.ClickHelpBtn -> {
                TODO("点击帮助按钮")
            }

            is LoginDirectEvent.ClickPrivacyBtn -> {
                _directLoginUiState.update {
                    it.copy(isPrivacySelected = event.isSelected)
                }
            }

            is LoginDirectEvent.ClickChangeAccount -> {
                TODO("点击切换账号")
            }
        }
    }

}