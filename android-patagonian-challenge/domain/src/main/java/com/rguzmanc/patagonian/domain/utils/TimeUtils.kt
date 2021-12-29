package com.rguzmanc.patagonian.domain.utils

object TimeUtils {

    fun getMinutesBetweenTimes(startDate: Long, endDate: Long): Long {
        val diff = endDate - startDate
        return diff / (1000 * 60)
    }
}