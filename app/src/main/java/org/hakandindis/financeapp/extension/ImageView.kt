package org.hakandindis.financeapp.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.hakandindis.financeapp.R
import org.hakandindis.financeapp.util.TransactionCategories


@BindingAdapter("set_transaction_category")
fun setTransactionCategory(view: ImageView, category: String) {
    when(category) {
        TransactionCategories.FUN.value -> view.setImageResource(R.drawable.fun_background)
        TransactionCategories.FOOD.value -> view.setImageResource(R.drawable.food_background)
        TransactionCategories.SUBSCRIPTION.value -> view.setImageResource(R.drawable.subscription_background)
        TransactionCategories.TRANSPORTATION.value -> view.setImageResource(R.drawable.transportation_background)
    }
}