package com.techyourchance.coroutines.demonstrations.incorrectparalleldecomposition

import kotlinx.coroutines.*
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

class IncorrectParallelDecompositionDemoTest {

    @Test
    fun wrongUseOfCoroutines() = runBlocking {
        var totalIterations = 0
        withContext(Dispatchers.Default) {
            for (duration in 1..5) {
                println("start + $duration")
                launch {
                    val startTimeNano = System.nanoTime()
                    var iterations = 0
                    while (System.nanoTime() < startTimeNano + (duration * 10f.pow(9))) {
                        iterations++
                    }
                    totalIterations += iterations
                }
                println("end + $duration")
            }
        }
        println("total iterations: $totalIterations")
    }
    //144419423

    @Test
    fun atomicityProblemDemo() = runBlocking {
        var totalIterations = 0
        withContext(Dispatchers.Default) {
            repeat(100) {
                launch {
                    delay(100)
                    totalIterations++
                }
            }
        }
        println("total iterations: $totalIterations")
    }


}