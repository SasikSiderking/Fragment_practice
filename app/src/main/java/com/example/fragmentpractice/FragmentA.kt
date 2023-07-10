package com.example.fragmentpractice

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentA : Fragment(R.layout.fragment_a) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            (requireActivity() as? NavigationListener)?.onFragmentANext()
        }
    }

    interface NavigationListener {
        fun onFragmentANext()
    }
}