package com.muggle.tiktokcopy.http

import com.google.gson.annotations.SerializedName

data class CommonResponse<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("data")
    val data: T?
)