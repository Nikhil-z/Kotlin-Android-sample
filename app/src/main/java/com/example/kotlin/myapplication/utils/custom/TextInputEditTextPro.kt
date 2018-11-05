package com.example.kotlin.myapplication.utils.custom

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet

open class TextInputEditTextPro : TextInputEditText {


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var minimumValidTextLength = 0
    private lateinit var textInputLayout: TextInputLayout
    private var minimumValidLengthErrorText = ""
    private var errorText = "Please fill up the mandatory field."


    internal fun setMinimumLength(length: Int) {
        this.minimumValidTextLength = length
    }

    internal fun setMinimumError(error: String) {
        this.minimumValidLengthErrorText = error
    }

    internal fun setTextInputLayout(textInputLayout: TextInputLayout) {
        this.textInputLayout = textInputLayout
        this.textInputLayout.isErrorEnabled = false
        enableProErrorHandler()
    }

    internal fun setErrorText(errorText: String) {
        this.errorText = errorText
    }

    private fun onChange(cb: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                cb(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun enableProErrorHandler() {

        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (textInputLayout.isErrorEnabled) {
                    textInputLayout.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    internal fun validate(){
        isValid()
    }

    internal fun isValid(): Boolean {

        var valid = false
        if (minimumValidTextLength > 0) {
            if (text!!.length < minimumValidTextLength) {
                textInputLayout.isErrorEnabled = true

                if (minimumValidLengthErrorText.isEmpty()) {
                    minimumValidLengthErrorText = "A minimum of $minimumValidTextLength character expected."
                }
                textInputLayout.error = minimumValidLengthErrorText
            }
        } else if (text!!.isEmpty()) {

            textInputLayout.isErrorEnabled = true
            textInputLayout.error = errorText

        } else {
            valid = true
        }
        return valid
    }


}