package com.muggle.tiktokcopy.business.home.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.home.repo.MineScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @date 2026/3/23 0:59
 * @author muggle
 * @desc
 */
@HiltViewModel
class MineScreenVm @Inject constructor(private val repo: MineScreenRepo) : ViewModel() {
}