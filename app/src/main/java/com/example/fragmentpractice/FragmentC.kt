package com.example.fragmentpractice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class FragmentC : Fragment(R.layout.fragment_c) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            (requireActivity() as? NavigationListener)?.onFragmentCNext()
        }
        view.findViewById<Button>(R.id.backToAButton).setOnClickListener {
            (requireActivity() as? NavigationListener)?.onFragmentCBackToA()
        }

        view.findViewById<TextView>(R.id.helloTextView).text =
            arguments?.getString(HELLO_TEXT_EXTRA)
    }

    interface NavigationListener {

        fun onFragmentCNext()

        fun onFragmentCBackToA()
    }

    companion object {

        const val FRAGMENT_C_TAG = "FRAGMENT_C_TAG"

        private const val HELLO_TEXT_EXTRA = "HELLO_TEXT_EXTRA"

        @JvmStatic
        fun newInstance(helloText: String) = FragmentC().apply {
            arguments = bundleOf(HELLO_TEXT_EXTRA to helloText)
        }
    }
}