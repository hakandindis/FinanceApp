package org.hakandindis.financeapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.hakandindis.financeapp.data.local.dao.TransactionDao
import org.hakandindis.financeapp.data.local.db.FinanceApplicationDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideFinanceApplicationDatabase(@ApplicationContext context: Context): FinanceApplicationDatabase {
        return Room.databaseBuilder(
            context,
            FinanceApplicationDatabase::class.java,
            "finance_application_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: FinanceApplicationDatabase): TransactionDao {
        return database.transactionDao()
    }

}