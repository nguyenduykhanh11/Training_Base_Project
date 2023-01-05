package com.beetech.hsba.extension

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.ViewPager
import com.beetech.hsba.BaseApplication.Companion.context
import com.beetech.hsba.R

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

const val CLICK_THROTTLE_DELAY = 800L

fun View.onAvoidDoubleClick(
    throttleDelay: Long = CLICK_THROTTLE_DELAY,
    onClick: (View) -> Unit
) {
    setOnClickListener {
        onClick(this)
        isClickable = false
        postDelayed({ isClickable = true }, throttleDelay)
    }
}

infix fun TextView.textChangedListener(init: TextWatcherWrapper.() -> Unit) {
    val wrapper = TextWatcherWrapper().apply { init() }
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable) {
            wrapper.after?.invoke(p0)
        }

        override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            wrapper.before?.invoke(p0, p1, p2, p3)
        }

        override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            wrapper.on?.invoke(p0, p1, p2, p3)
        }

    })
}

infix fun ViewPager.pageChangeListener(init: OnPageChangeListenerWrapper.() -> Unit) {
    val wrapper = OnPageChangeListenerWrapper().apply { init() }
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            wrapper.onPageScrollStateChanged?.invoke(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            wrapper.onPageScrolled?.invoke(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            wrapper.onPageSelected?.invoke(position)
        }

    })
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) { }
    return false
}

val EditText.stringVal: String
    get() = this.text.toString().trim()

enum class Category{
    EmptyAccount,
    EmptyPassword,
    EmptyAll,
    Successfully,
    Logged
}
val Category.CategoryError: String
    get() = when (this) {
        Category.EmptyAccount -> context.getString(R.string.str_empty_username)
        Category.EmptyPassword -> context.getString(R.string.str_empty_password)
        Category.EmptyAll -> context.getString(R.string.str_empty_all)
        Category.Successfully -> context.getString(R.string.str_login_success)
        Category.Logged -> context.getString(R.string.lable_Logged)
    }

enum class SharePref{
    MyPref,
    KeyPref}

val SharePref.CategotySharePref: String
    get() = when (this) {
        SharePref.MyPref -> context.getString(R.string.dialog_title_key_pref)
        SharePref.KeyPref -> context.getString(R.string.dialog_title_key_pref)}

val KEY_BUNDLE = context.getString(R.string.tab_status_tablayout)
