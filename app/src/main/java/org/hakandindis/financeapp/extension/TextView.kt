package org.hakandindis.financeapp.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.hakandindis.financeapp.R
import org.hakandindis.financeapp.scene.model.TransactionModel
import org.hakandindis.financeapp.util.TransactionTypes

@BindingAdapter("set_transaction_amount")
fun setTransactionAmount(view: TextView, model: TransactionModel) {
    val color = when (model.transactionType) {
        TransactionTypes.INCOME.value -> view.context.resources.getColor(R.color.green)
        else -> view.context.resources.getColor(R.color.red)
    }

    view.setTextColor(color)
    view.text = "${model.transactionAmount} TL"
}