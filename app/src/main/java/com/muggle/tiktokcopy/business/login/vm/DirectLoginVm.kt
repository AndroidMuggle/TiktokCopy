package com.muggle.tiktokcopy.business.login.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.login.intent.LoginDirectEvent
import com.muggle.tiktokcopy.business.login.repo.LoginRepo
import com.muggle.tiktokcopy.business.login.state.DirectLoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DirectLoginVm @Inject constructor(private val repo: LoginRepo) : ViewModel() {

    private val _directLoginState = mutableStateOf(DirectLoginState())
    val directLoginState: State<DirectLoginState> = _directLoginState

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
                _directLoginState.value =
                    _directLoginState.value.copy(isPrivacySelected = event.isSelected)
            }

            is LoginDirectEvent.ClickChangeAccount -> {
                TODO("点击切换账号")
            }
        }
    }

}