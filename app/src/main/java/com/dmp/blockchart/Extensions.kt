package com.dmp.blockchart

import android.view.View
import androidx.core.content.ContextCompat

// region View Extensions
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}
// endregion View Extensions

// region Integer Extensions
fun Int.asString(): String
{
    return BlockChartApplication.getContext().getString(this)
}

fun Int.asColor(): Int
{
    return ContextCompat.getColor(BlockChartApplication.getContext(), this)
}
// endregion Integer Extensions