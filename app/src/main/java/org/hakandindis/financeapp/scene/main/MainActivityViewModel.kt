package org.hakandindis.financeapp.scene.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    val isAuthenticated = MutableLiveData(false)
}