package org.hakandindis.financeapp.extension

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import org.hakandindis.financeapp.BuildConfig
import org.hakandindis.financeapp.R
import org.hakandindis.financeapp.util.TransactionCategories


@BindingAdapter("set_transaction_category")
fun setTransactionCategory(view: ImageView, category: String) {
    when (category) {
        TransactionCategories.FUN.value -> view.setImageResource(R.drawable.fun_background)
        TransactionCategories.FOOD.value -> view.setImageResource(R.drawable.food_background)
        TransactionCategories.SUBSCRIPTION.value -> view.setImageResource(R.drawable.subscription_background)
        TransactionCategories.TRANSPORTATION.value -> view.setImageResource(R.drawable.transportation_background)
    }
}

@BindingAdapter("load_coin_image")
fun loadCoinImage(imageView: ImageView, id: String) {
    imageView.loadImage(id)
}

fun ImageView.loadImage(id: String?) = this.load(BuildConfig.BASE_IMAGE_URL.plus("$id.png")) {
    crossfade(true)
    crossfade(500)
    placeholder(createPlaceHolder(context))
}

private fun createPlaceHolder(context: Context) = CircularProgressDrawable(context).apply {
    strokeWidth = 12f
    centerRadius = 40f
    start()
}