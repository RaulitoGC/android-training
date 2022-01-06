package com.techyourchance.coroutines.challenge.ride

import com.techyourchance.coroutines.challenge.ride.TimeParameter.FIVE_SECONDS
import com.techyourchance.coroutines.challenge.ride.TimeParameter.LESS_THAN_FIVE_SECONDS
import com.techyourchance.coroutines.challenge.ride.TimeParameter.MORE_THAN_FIVE_SECONDS
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test


class AcceptRiderUseCaseTest {

    private lateinit var acceptRideUseCase: AcceptRideUseCase

    @Before
    fun setUp(){
        acceptRideUseCase = AcceptRideUseCase()
    }

    @Test
    fun `first ride less than five and second ride with more than five seconds then return true`() = runBlocking {
        val rideOne = RiderOne("riderOne", LESS_THAN_FIVE_SECONDS, true)
        val rideTwo = RiderOne("riderTwo", MORE_THAN_FIVE_SECONDS, true)
        assert(acceptRideUseCase.invoke(rideOne,rideTwo))
    }

    @Test
    fun `first ride more than five and second ride with more than five seconds then return false`() = runBlocking {
        val rideOne = RiderOne("riderOne", MORE_THAN_FIVE_SECONDS, true)
        val rideTwo = RiderOne("riderTwo", MORE_THAN_FIVE_SECONDS, true)
        assert(acceptRideUseCase.invoke(rideOne,rideTwo).not())
    }

    @Test
    fun `first ride less than five and second ride with less than five seconds then return true`() = runBlocking {
        val rideOne = RiderOne("riderOne", LESS_THAN_FIVE_SECONDS, true)
        val rideTwo = RiderOne("riderTwo", LESS_THAN_FIVE_SECONDS, true)
        assert(acceptRideUseCase.invoke(rideOne,rideTwo))
    }

    @Test
    fun `first ride more than five and second ride with less than five seconds then return true`() = runBlocking {
        val rideOne = RiderOne("riderOne", MORE_THAN_FIVE_SECONDS, true)
        val rideTwo = RiderOne("riderTwo", LESS_THAN_FIVE_SECONDS, true)
        assert(acceptRideUseCase.invoke(rideOne,rideTwo))
    }

    @Test
    fun `first ride exact five seconds and second ride with less than five seconds then return true`() = runBlocking {
        val rideOne = RiderOne("riderOne", FIVE_SECONDS, true)
        val rideTwo = RiderOne("riderTwo", LESS_THAN_FIVE_SECONDS, true)
        assert(acceptRideUseCase.invoke(rideOne,rideTwo))
    }

    @Test
    fun `first ride false response and second ride true response then return true`() = runBlocking {
        val rideOne = RiderOne("riderOne", LESS_THAN_FIVE_SECONDS, false)
        val rideTwo = RiderOne("riderTwo", MORE_THAN_FIVE_SECONDS, true)
        assert(acceptRideUseCase.invoke(rideOne,rideTwo).not())
    }
}