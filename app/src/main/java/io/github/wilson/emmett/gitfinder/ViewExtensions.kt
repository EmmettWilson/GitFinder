package io.github.wilson.emmett.gitfinder

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.showKeyboard() {
    if (requestFocus()) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.circularReveal(xCoordinate: Int, yCoordinate: Int, endRadius: Int, postAnimationFunction: () -> Unit) {
    visible()
    val animator = ViewAnimationUtils.createCircularReveal(
        this,
        xCoordinate,
        yCoordinate,
        0f,
        endRadius.toFloat()
    )
    animator.interpolator = AccelerateDecelerateInterpolator()
    animator.duration = resources.getInteger(android.R.integer.config_longAnimTime).toLong()
    animator.addPostAnimationListener {
        postAnimationFunction()
    }
    animator.start()
}

fun View.circularCollapse(xCoordinate: Int, yCoordinate: Int, startRadius: Int, postAnimationFunction: () -> Unit) {
    val anim = ViewAnimationUtils.createCircularReveal(
        this,
        xCoordinate,
        yCoordinate,
        startRadius.toFloat(),
        0f
    )

    anim.interpolator = AccelerateDecelerateInterpolator()
    anim.duration = resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    anim.addPostAnimationListener {
        invisible()
        postAnimationFunction()
    }
    anim.start()
}

fun View.onAttachedToWindow(block: () -> Unit) {
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(v: View) = Unit
        override fun onViewAttachedToWindow(v: View) {
            block()
            removeOnAttachStateChangeListener(this)
        }
    })
}

fun Activity.measureDisplay(): DisplayMetrics {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics
}

fun Animator.addPostAnimationListener(block: () -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) = Unit
        override fun onAnimationEnd(animation: Animator?) = block()
        override fun onAnimationCancel(animation: Animator?) = Unit
        override fun onAnimationStart(animation: Animator?) = Unit
    })
}
