package com.rguzmanc.friends.login.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginApi {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<Any>?>?
}