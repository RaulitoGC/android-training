/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.cryptostonksfivethousand.presentation.coinlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raywenderlich.android.cryptostonksfivethousand.domain.Result
import com.raywenderlich.android.cryptostonksfivethousand.domain.model.Coin
import com.raywenderlich.android.cryptostonksfivethousand.domain.repositories.CoinsRepository
import com.raywenderlich.android.cryptostonksfivethousand.presentation.coinlist.mappers.UiCoinMapper
import com.raywenderlich.android.cryptostonksfivethousand.presentation.coinlist.model.UiCoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinListFragmentViewModel(
    private val coinsRepository: CoinsRepository,
    private val uiCoinMapper: UiCoinMapper
): ViewModel() {

  val viewState: StateFlow<CoinListFragmentViewState> get() = _viewState

  private val _viewState = MutableStateFlow(CoinListFragmentViewState())


  fun requestCoinList() {
    if (viewState.value.coins.isNotEmpty()) return

    viewModelScope.launch {
      when (val result = coinsRepository.getCoins()) {
        is Result.Success -> handleCoinList(result.value)
        is Result.Failure -> handleFailure(result.cause)
      }
    }
  }

  private fun handleCoinList(coins: List<Coin>) {
    val uiCoins = coins.map { uiCoinMapper.toUi(it) }

    _viewState.value = CoinListFragmentViewState(loading = false, coins = uiCoins)
  }

  private suspend fun handleFailure(cause: Throwable) {
    // Handle failures
  }
}