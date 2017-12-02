package com.infotechincubator.sharmal.extension

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by kyawagwin on 29/11/17.
 */
var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if(value) View.VISIBLE else View.GONE
    }

fun Context.inflateLayout(layoutResId: Int): View =
        inflateView(this, layoutResId, null, false)

fun Context.inflateLayout(layoutResId: Int, parent: ViewGroup): View =
        inflateLayout(layoutResId, parent, true)

fun Context.inflateLayout(layoutResId: Int, parent: ViewGroup, attachToRoot: Boolean): View =
        inflateView(this, layoutResId, parent, attachToRoot)

private fun inflateView(context: Context, layoutResId: Int, parent: ViewGroup?, attachToRoot: Boolean): View =
        LayoutInflater.from(context).inflate(layoutResId, parent, attachToRoot)

fun ImageView.loadImage(url: String) {
    Picasso.with(context).load(url).into(this)
}

fun View.showSnackbar(message: String, length: Int = Snackbar.LENGTH_LONG, f: (Snackbar.() -> Unit) = {}) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun View.showSnackbar(@StringRes message: Int, length: Int = Snackbar.LENGTH_LONG, f: (Snackbar.() -> Unit) = {}) {
    showSnackbar(resources.getString(message), length, f)
}

fun Snackbar.action(action: String, @ColorInt color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}