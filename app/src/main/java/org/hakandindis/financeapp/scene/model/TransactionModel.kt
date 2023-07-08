package org.hakandindis.financeapp.scene.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.hakandindis.financeapp.data.local.model.TransactionEntity

@Parcelize
data class TransactionModel(
    val id: Int,
    val transactionName: String,
    val transactionAmount: Int,
    val transactionType: String,
    val transactionCategory: String
) : Parcelable {
    fun toEntity() = TransactionEntity(id, transactionName, transactionAmount, transactionType, transactionCategory)
}
