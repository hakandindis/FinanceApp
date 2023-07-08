package org.hakandindis.financeapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.hakandindis.financeapp.data.local.dao.TransactionDao
import org.hakandindis.financeapp.data.local.model.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1)
abstract class FinanceApplicationDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}