package org.hakandindis.financeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import org.hakandindis.financeapp.data.local.model.TransactionEntity

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(entity: TransactionEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(entity: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(entity: TransactionEntity)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<TransactionEntity>
}