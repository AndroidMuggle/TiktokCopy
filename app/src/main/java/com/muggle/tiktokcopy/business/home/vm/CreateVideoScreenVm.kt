package com.muggle.tiktokcopy.business.home.vm

import androidx.lifecycle.ViewModel
import com.muggle.tiktokcopy.business.home.repo.CreateVideoScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @date 2026/3/23 0:57
 * @author muggle
 * @desc
 */
@HiltViewModel
class CreateVideoScreenVm @Inject constructor(private val repo: CreateVideoScreenRepo) : ViewModel() {
}