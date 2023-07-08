package org.hakandindis.financeapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.hakandindis.financeapp.scene.model.TransactionModel


@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "transaction_name") val transactionName: String,
    @ColumnInfo(name = "transaction_amount") val transactionAmount: Int,
    @ColumnInfo(name = "transaction_type") val transactionType: String,
    @ColumnInfo(name = "transaction_category") val transactionCategory: String
) {
    fun toExternalModel() = TransactionModel(id!!, transactionName, transactionAmount, transactionType, transactionCategory)
}