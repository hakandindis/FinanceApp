package org.hakandindis.financeapp.scene.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.financeapp.data.local.dao.TransactionDao
import org.hakandindis.financeapp.scene.model.TransactionModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val transactionDao: TransactionDao): ViewModel() {

    private var _recentTransactions = MutableLiveData<List<TransactionModel>?>()
    val recentTransactions : LiveData<List<TransactionModel>?>
        get() = _recentTransactions

    init {
        loadRecentTransactions()
    }

    fun loadRecentTransactions() {
        viewModelScope.launch {
            try {
                val list = transactionDao.getAllTransactions()
                val convertedList = list.map { it.toExternalModel() }.toList()
                _recentTransactions.value = convertedList
            } catch (e: Exception) {}
        }
    }

}