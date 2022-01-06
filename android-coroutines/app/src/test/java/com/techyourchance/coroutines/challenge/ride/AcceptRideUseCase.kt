package com.techyourchance.coroutines.challenge.ride

import com.techyourchance.coroutines.challenge.ride.TimeParameter.FIVE_SECONDS
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.withTimeout

class AcceptRideUseCase {

    suspend fun invoke(riderOne: Rider, riderTwo: Rider): Boolean {
        val responseOne = GlobalScope.async {
            return@async try{
                withTimeout(FIVE_SECONDS){
                    riderOne.getResponse()
                }
            } catch (e: TimeoutCancellationException){
                false
            }
        }

        val responseTwo = GlobalScope.async {
            return@async try{
                withTimeout(FIVE_SECONDS){
                    riderTwo.getResponse()
                }
            } catch (e: TimeoutCancellationException){
                 false
            }
        }


        return responseOne.await() || responseTwo.await()
    }
}