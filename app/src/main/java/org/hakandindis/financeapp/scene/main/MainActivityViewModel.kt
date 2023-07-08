package org.hakandindis.financeapp.scene.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivityViewModel: ViewModel() {
    val isAuthenticated = MutableLiveData(false)
}