package com.rguzmanc.patagonian.presentetation.util

object MathUtils {

    fun toDegrees(value: Float) = Math.toDegrees(value + 360.0) % 360
}