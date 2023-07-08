package org.hakandindis.financeapp.scene.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.financeapp.data.local.dao.TransactionDao
import org.hakandindis.financeapp.scene.model.TransactionModel
import org.hakandindis.financeapp.util.TransactionTypes
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val transactionDao: TransactionDao) : ViewModel() {

    private var _recentTransactions = MutableLiveData<List<TransactionModel>?>()
    val recentTransactions: LiveData<List<TransactionModel>?>
        get() = _recentTransactions

    val totalBalance = MutableLiveData<Int>(0)
    val totalIncome = MutableLiveData<Int>(0)
    val totalExpense = MutableLiveData<Int>(0)

    init {
        loadRecentTransactions()
    }

    fun loadRecentTransactions() {
        viewModelScope.launch {
            try {
                val list = transactionDao.getAllTransactions()
                var convertedList = mutableListOf<TransactionModel>()
                var income = 0
                var expense = 0

                for (model in list) {
                    if (model.transactionType == TransactionTypes.INCOME.value) {
                        income += model.transactionAmount
                    } else {
                        expense += model.transactionAmount
                    }
                    convertedList.add(model.toExternalModel())
                }

                _recentTransactions.value = convertedList
                totalBalance.value = income - expense
                totalIncome.value = income
                totalExpense.value = expense
            } catch (e: Exception) {
            }
        }
    }

}