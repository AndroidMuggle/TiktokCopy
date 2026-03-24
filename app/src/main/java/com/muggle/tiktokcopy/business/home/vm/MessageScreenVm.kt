package com.muggle.tiktokcopy.business.home.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.home.repo.MessageScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @date 2026/3/23 0:58
 * @author muggle
 * @desc
 */
@HiltViewModel
class MessageScreenVm @Inject constructor(private val repo: MessageScreenRepo) : ViewModel() {
}