package org.hakandindis.financeapp.scene.transaction_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hakandindis.financeapp.data.local.dao.TransactionDao
import org.hakandindis.financeapp.scene.model.TransactionModel
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(private val transactionDao: TransactionDao) : ViewModel() {


    fun deleteTransaction(model: TransactionModel) {
        viewModelScope.launch {
            transactionDao.deleteTransaction(model.toEntity())
        }
    }
}