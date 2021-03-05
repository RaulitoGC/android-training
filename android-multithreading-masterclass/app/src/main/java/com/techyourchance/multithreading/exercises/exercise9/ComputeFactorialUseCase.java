package com.techyourchance.multithreading.exercises.exercise9;

import android.os.Handler;
import android.os.Looper;

import java.math.BigInteger;
import java.util.concurrent.Callable;

import androidx.annotation.WorkerThread;

import io.reactivex.Observable;

public class ComputeFactorialUseCase {

    public static class Result {
        private final BigInteger mComputeResult;
        private final boolean mTimeOut;
        private final boolean mAborted;

        public Result(BigInteger mComputeResult, boolean mTimeOut, boolean mAborted) {
            this.mComputeResult = mComputeResult;
            this.mTimeOut = mTimeOut;
            this.mAborted = mAborted;
        }

        public boolean isAborted(){
            return mAborted;
        }

        public boolean isTimeOut(){
            return mTimeOut;
        }

        public BigInteger getResult(){
            return mComputeResult;
        }
    }

    private final Object LOCK = new Object();

    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    private int mNumberOfThreads;
    private ComputationRange[] mThreadsComputationRanges;
    private volatile BigInteger[] mThreadsComputationResults;
    private int mNumOfFinishedThreads = 0;

    private long mComputationTimeoutTime;

    private boolean mAbortComputation;

    public Observable<Result> computeFactorialAndNotify(final int argument, final int timeout) {
        return Observable.fromCallable(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                initComputationParams(argument, timeout);

                for (int i = 0; i < mNumberOfThreads; i++) {

                    final int threadIndex = i;

                    new Thread(() -> {
                        long rangeStart = mThreadsComputationRanges[threadIndex].start;
                        long rangeEnd = mThreadsComputationRanges[threadIndex].end;
                        BigInteger product = new BigInteger("1");
                        for (long num = rangeStart; num <= rangeEnd; num++) {
                            if (isTimedOut()) {
                                break;
                            }
                            product = product.multiply(new BigInteger(String.valueOf(num)));
                        }
                        mThreadsComputationResults[threadIndex] = product;

                        synchronized (LOCK) {
                            mNumOfFinishedThreads++;
                            LOCK.notifyAll();
                        }

                    }).start();
                }

                waitForThreadsResultsOrTimeoutOrAbort();

                if (mAbortComputation) {
                    return new Result(BigInteger.valueOf(0), false, true);
                }

                BigInteger result = computeFinalResult();

                // need to check for timeout after computation of the final result
                if (isTimedOut()) {
                    return new Result(BigInteger.valueOf(0), true, false);
                }

                return new Result(result, false, false);

            }
        });
    }

    private void initComputationParams(int factorialArgument, int timeout) {
        mNumberOfThreads = factorialArgument < 20
                ? 1 : Runtime.getRuntime().availableProcessors();

        synchronized (LOCK) {
            mNumOfFinishedThreads = 0;
            mAbortComputation = false;
        }

        mThreadsComputationResults = new BigInteger[mNumberOfThreads];

        mThreadsComputationRanges = new ComputationRange[mNumberOfThreads];

        initThreadsComputationRanges(factorialArgument);

        mComputationTimeoutTime = System.currentTimeMillis() + timeout;
    }

    private void initThreadsComputationRanges(int factorialArgument) {
        int computationRangeSize = factorialArgument / mNumberOfThreads;

        long nextComputationRangeEnd = factorialArgument;
        for (int i = mNumberOfThreads - 1; i >= 0; i--) {
            mThreadsComputationRanges[i] = new ComputationRange(
                    nextComputationRangeEnd - computationRangeSize + 1,
                    nextComputationRangeEnd
            );
            nextComputationRangeEnd = mThreadsComputationRanges[i].start - 1;
        }

        // add potentially "remaining" values to first thread's range
        mThreadsComputationRanges[0].start = 1;
    }

    @WorkerThread
    private void waitForThreadsResultsOrTimeoutOrAbort() {
        synchronized (LOCK) {
            while (mNumOfFinishedThreads != mNumberOfThreads && !mAbortComputation && !isTimedOut()) {
                try {
                    LOCK.wait(getRemainingMillisToTimeout());
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    @WorkerThread
    private BigInteger computeFinalResult() {
        BigInteger result = new BigInteger("1");
        for (int i = 0; i < mNumberOfThreads; i++) {
            if (isTimedOut()) {
                break;
            }
            result = result.multiply(mThreadsComputationResults[i]);
        }
        return result;
    }

    private long getRemainingMillisToTimeout() {
        return mComputationTimeoutTime - System.currentTimeMillis();
    }

    private boolean isTimedOut() {
        return System.currentTimeMillis() >= mComputationTimeoutTime;
    }

    public void cancelComputation(){
        synchronized (LOCK) {
            mAbortComputation = true;
            LOCK.notifyAll();
        }
    }

    private static class ComputationRange {
        private long start;
        private long end;

        public ComputationRange(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
