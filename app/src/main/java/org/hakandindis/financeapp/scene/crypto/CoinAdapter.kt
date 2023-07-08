package org.hakandindis.financeapp.scene.crypto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.hakandindis.financeapp.data.remote.model.Coin
import org.hakandindis.financeapp.databinding.RowCoinItemBinding

class CoinAdapter(private val listener: CoinClickListener) : ListAdapter<Coin, CoinAdapter.CoinViewHolder>(coinDiffUtil) {

    companion object {
        val coinDiffUtil = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin) = (oldItem.id == newItem.id)
            override fun areContentsTheSame(oldItem: Coin, newItem: Coin) = (oldItem == newItem)
        }
    }

    inner class CoinViewHolder(val binding: RowCoinItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowCoinItemBinding.inflate(inflater, parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.binding.coin = currentList[position]
        holder.binding.root.setOnClickListener { listener.onCoinShortClick() }
    }
}

interface CoinClickListener {
    fun onCoinShortClick()
}