package com.muggle.tiktokcopy.business.login.repo

import com.muggle.tiktokcopy.business.login.bean.LoginRequestBean
import com.muggle.tiktokcopy.business.login.bean.LoginResponseBean
import com.muggle.tiktokcopy.http.CommonHttpInterface
import com.muggle.tiktokcopy.http.CommonResponse
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepo @Inject constructor() {

    suspend fun loginByPassword(loginRequestBean: LoginRequestBean): CommonResponse<LoginResponseBean>? {
        return CommonHttpInterface.startHttpRequest<LoginResponseBean>(
            httpRequestBuilder = HttpRequestBuilder().apply {
                url {
                    protocol = URLProtocol.HTTP
                    host = SERVER_HOST
                    port = SERVER_PORT
                    pathSegments = arrayListOf("login/loginByPassword")
                }
                method = HttpMethod.Get
                setBody(loginRequestBean)
            }
        ).getOrNull()
    }

    /**
     * 获取url
     */
    private fun getUrl(path: String = ""): String {
        return SERVER_HOST + path
    }


    companion object {
        private const val TAG = "LoginRepo"
        private const val SERVER_HOST = "192.168.1.4"
        private const val SERVER_PORT = 8080
        private const val LOGIN_BY_PASSWORD = "/login/loginByPassword"
    }
}