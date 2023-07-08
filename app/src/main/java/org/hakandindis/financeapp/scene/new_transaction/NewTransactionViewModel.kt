package org.hakandindis.financeapp.scene.new_transaction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.financeapp.data.local.dao.TransactionDao
import org.hakandindis.financeapp.data.local.model.TransactionEntity
import javax.inject.Inject


@HiltViewModel
class NewTransactionViewModel @Inject constructor(private val transactionDao: TransactionDao) : ViewModel() {

    fun addTransaction(transactionName: String, transactionAmount: Int, transactionType: String, transactionCategory: String) {
        viewModelScope.launch {

            transactionDao.insertTransaction(
                TransactionEntity(
                    transactionName = transactionName,
                    transactionAmount = transactionAmount,
                    transactionType = transactionType,
                    transactionCategory = transactionCategory
                )
            )

            val list = transactionDao.getAllTransactions()
            list.forEach { Log.d("XATIRO", it.toString()) }
        }
    }
}