package org.hakandindis.financeapp.scene.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.hakandindis.financeapp.databinding.RowRecentTransactionItemBinding
import org.hakandindis.financeapp.scene.model.TransactionModel

class RecentTransactionsAdapter : ListAdapter<TransactionModel, RecentTransactionsAdapter.RecentTransactionViewHolder>(recentTransactionDiffUtil) {

    companion object {
        val recentTransactionDiffUtil = object : DiffUtil.ItemCallback<TransactionModel>() {
            override fun areItemsTheSame(oldItem: TransactionModel, newItem: TransactionModel) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: TransactionModel, newItem: TransactionModel) = oldItem == newItem
        }
    }

    inner class RecentTransactionViewHolder(val binding: RowRecentTransactionItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentTransactionViewHolder {
        val binding = RowRecentTransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentTransactionViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: RecentTransactionViewHolder, position: Int) {
        holder.binding.model = currentList[position]
    }
}