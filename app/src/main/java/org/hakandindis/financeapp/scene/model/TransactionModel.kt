package org.hakandindis.financeapp.scene.model

data class TransactionModel(
    val id: Int,
    val transactionName: String,
    val transactionAmount: Int,
    val transactionType: String,
    val transactionCategory: String
)
