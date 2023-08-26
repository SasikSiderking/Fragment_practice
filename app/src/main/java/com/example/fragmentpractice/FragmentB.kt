package com.example.fragmentpractice

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentB : Fragment(R.layout.fragment_b) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            findViewById<Button>(R.id.nextButton).setOnClickListener {
                (requireActivity() as? NavigationListener)?.onFragmentBNext(HELLO_TEXT)
            }
            findViewById<Button>(R.id.backToAButton).setOnClickListener {
                (requireActivity() as? NavigationListener)?.onFragmentBBack()
            }
        }
    }

    interface NavigationListener {

        fun onFragmentBNext(helloText: String)

        fun onFragmentBBack()
    }

    companion object {

        const val HELLO_TEXT = "Hello Fragment C"

        const val FRAGMENT_B_TAG = "FRAGMENT_B_TAG"

        @JvmStatic
        fun newInstance() = FragmentB()
    }
}