package com.rguzmanc.patagonian.domain.repository

import kotlinx.coroutines.flow.flow

class FakeRepository : PatagonianRepository {

    companion object {

        private var lastSessionTime = 0L
        private var sessionCounter = 0

        fun reset() {
            lastSessionTime = 0L
            sessionCounter = 0
        }

        fun setSessionCount(sessionCount: Int){
            this.sessionCounter = sessionCount
        }
    }

    override suspend fun getSessionCount() = flow {
        emit(sessionCounter)
    }

    override suspend fun increment() {
        sessionCounter += 1
    }

    override suspend fun setLastSessionTime(sessionTime: Long) {
        lastSessionTime = sessionTime
    }

    override suspend fun getLastSessionTime() = flow {
        emit(lastSessionTime)
    }
}