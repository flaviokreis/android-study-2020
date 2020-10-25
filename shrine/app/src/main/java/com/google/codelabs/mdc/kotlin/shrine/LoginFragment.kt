package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.shr_login_fragment.*
import kotlinx.android.synthetic.main.shr_login_fragment.view.*

class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        view.next_button.setOnClickListener {
            tryLogin()
        }

        view.password_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(text: Editable?) {
                if (isPasswordValid(text)) {
                    // Clear the error.
                    password_text_input.error = null
                }
            }
        })

        view.password_edit_text.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                tryLogin()
            }
            false
        }

        return view
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun tryLogin() {
        if (!isPasswordValid(password_edit_text.text)) {
            password_text_input.error = getString(R.string.shr_error_password)
        } else {
            // Clear the error.
            password_text_input.error = null
            // Navigate to the next Fragment.
            (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
        }
    }
}
