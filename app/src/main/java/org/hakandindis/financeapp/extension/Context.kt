package org.hakandindis.financeapp.extension

import android.content.Context
import android.widget.Toast


fun Context.showEmailOrPasswordNotValidToast() {
    Toast.makeText(this, "email veya şifre yanlış", Toast.LENGTH_LONG).show()
}

fun Context.showEmailIsEmptyToast() {
    Toast.makeText(this, "mail kısmı boş bırakılamaz", Toast.LENGTH_LONG).show()
}

fun Context.showPasswordIsEmptyToast() {
    Toast.makeText(this, "şifre kısmı boş bırakılamaz", Toast.LENGTH_LONG).show()
}
