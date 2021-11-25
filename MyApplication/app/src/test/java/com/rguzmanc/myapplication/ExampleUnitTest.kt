package com.rguzmanc.myapplication

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 *  Using idomatic Kotlin, solve the following: given two integers implement a function which determines if they have the same frequency of digits.
equalDigitFrequency("2234", "4223") //true
equalDigitFrequency("1223", "123") //false
private fun equalDigitFrequency(i1: Int, i2: Int): Boolean {
TODO("not implemented")
}
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun isEqualsDigitsFrequency_different_length() {
        val first = 12
        val second = 1221
        assertEquals(equalDigitFrequency(first, second), false)
    }

    @Test
    fun isEqualsDigitsFrequency() {
        val first = 1223
        val second = 4223
        assertEquals(equalDigitFrequency(first, second), false)
    }

    @Test
    fun isEqualsDigitsFrequency_sameNumber() {
        val first = 111111
        val second = 111111
        assertEquals(equalDigitFrequency(first, second), true)
    }

    @Test
    fun isEqualsDigitsFrequency_zero_case() {
        val first = 0
        val second = 4223
        assertEquals(equalDigitFrequency(first, second), false)
    }

    @Test
    fun isEqualsDigitsFrequency_zero_both_number() {
        val first = 0
        val second = 0
        assertEquals(equalDigitFrequency(first, second), true)
    }

    private fun getCountNumber(n: Int): HashMap<Int, Int> {
        var number = n
        val count = HashMap<Int, Int>()
        while (number != 0) {
            val mod = number % 10
            val current = count.getOrDefault(mod, 0)
            count[mod] = current + 1
            number /= 10
        }

        return count
    }

    private fun getCountNumberKotlinVersion(n: String): Map<Char, Int> {
        return n.groupingBy {
            it
        }.eachCount()
    }

    private fun equalDigitFrequency(i1: Int, i2: Int): Boolean {
        if(i1.toString().length != i2.toString().length) return false

        val fistCount = getCountNumberKotlinVersion(i1.toString())
        val secondCount = getCountNumberKotlinVersion(i2.toString())

        return fistCount == secondCount
    }
}