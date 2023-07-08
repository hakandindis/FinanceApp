package org.hakandindis.financeapp.scene.crypto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.financeapp.BuildConfig
import org.hakandindis.financeapp.data.remote.model.Coin
import org.hakandindis.financeapp.data.remote.service.CoinService
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(private val coinService: CoinService): ViewModel() {
    private val _coins: MutableLiveData<List<Coin?>?> = MutableLiveData()
    val coins: LiveData<List<Coin?>?> get() = _coins


    init {
        getLatestCoins()
    }

    private fun getLatestCoins() {
        viewModelScope.launch {
            val response = coinService.getLatestCoins(apiKey = BuildConfig.API_KEY, "20")
            _coins.value = response.data
        }
    }
}