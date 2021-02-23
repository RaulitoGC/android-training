package com.techyourchance.multithreading.exercises.exercise1;

import android.util.Log;

public class CounterIterationRunnable implements Runnable {

    private int iterationCounterDuration;

    public CounterIterationRunnable(int iterationCounterDuration) {
        this.iterationCounterDuration = iterationCounterDuration;
    }


    @Override
    public void run() {
        long startTimestamp = System.currentTimeMillis();
        long endTimestamp = startTimestamp + iterationCounterDuration * 1000;

        int iterationsCount = 0;
        while (System.currentTimeMillis() <= endTimestamp) {
            iterationsCount++;
        }

        Log.d(
                "Exercise1",
                "iterations in " + iterationCounterDuration + "seconds: " + iterationsCount
        );
    }
}
