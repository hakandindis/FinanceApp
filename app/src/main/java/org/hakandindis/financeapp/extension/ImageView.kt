package org.hakandindis.financeapp.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.hakandindis.financeapp.R


@BindingAdapter("set_transaction_category")
fun setTransactionCategory(view: ImageView, category: String) {
    when(category) {
        "Subscription" -> view.setImageResource(R.drawable.subscription_icon)
        "Rent" -> view.setImageResource(R.drawable.baseline_house_24)
        "Food" -> view.setImageResource(R.drawable.food_icon)
        "Clothes" -> view.setImageResource(R.drawable.income_arrow_icon)
    }
}