package com.muggle.tiktokcopy.business.home.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.home.repo.FriendScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @date 2026/3/23 0:54
 * @author muggle
 * @desc
 */
@HiltViewModel
class FriendScreenVm @Inject constructor(private val repo: FriendScreenRepo) : ViewModel() {
}