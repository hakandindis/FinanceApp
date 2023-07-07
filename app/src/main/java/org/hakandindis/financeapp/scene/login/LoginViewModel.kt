package org.hakandindis.financeapp.scene.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.hakandindis.financeapp.util.AuthStates
import java.lang.Exception

class LoginViewModel: ViewModel() {

    lateinit var auth: FirebaseAuth
    val authState = MutableLiveData<AuthStates>()

    fun loginWithEmailAndPassword(email:String, password:String) {
        viewModelScope.launch {
            try {
                authState.value = AuthStates.LOADING
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        authState.value = AuthStates.SUCCESS
                    } else {
                        authState.value = AuthStates.FAILED
                    }
                }
            } catch (e: Exception) {
                authState.value = AuthStates.FAILED
            }
        }
    }
}