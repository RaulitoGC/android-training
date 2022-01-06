package com.techyourchance.coroutines.challenge.ride

import kotlinx.coroutines.delay

object TimeParameter{

    const val LESS_THAN_FIVE_SECONDS = 4000L
    const val MORE_THAN_FIVE_SECONDS = 7000L
    const val FIVE_SECONDS = 5000L
}

interface Rider{

    val timeToResponse: Long
    val response: Boolean
    val name: String

    suspend fun getResponse(): Boolean
}

data class RiderOne(override val name: String, override val timeToResponse: Long = 0L,override val response: Boolean) : Rider{

    override suspend fun getResponse(): Boolean {
        delay(timeToResponse)
        return response
    }
}

data class RiderTwo(override val name: String, override val timeToResponse: Long = 0L,override val response: Boolean) : Rider{

    override suspend fun getResponse(): Boolean {
        delay(timeToResponse)
        return response
    }
}