package com.rguzmanc.friends.service.di

import com.rguzmanc.friends.core.ApiEndPoint
import com.rguzmanc.friends.core.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class DefaultServiceModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient, @ApiEndPoint url: String): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .build()
    }
}
