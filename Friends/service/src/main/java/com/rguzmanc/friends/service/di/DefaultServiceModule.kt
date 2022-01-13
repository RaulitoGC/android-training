package com.rguzmanc.friends.service.di

import com.rguzmanc.friends.core.ApiEndPoint
import com.rguzmanc.friends.core.ApplicationScope
import com.rguzmanc.friends.core.BuildConfigDebug
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DefaultServiceModule {


    @Provides
    @ApplicationScope
    fun provideOkHttpClient(@BuildConfigDebug debug: Boolean): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (debug) {
                    eventListenerFactory(LoggingEventListener.Factory())
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.HEADERS
                    })
                }
            }
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient, @ApiEndPoint url: String): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .build()
    }
}
