package com.example.fragmentpractice

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentD : Fragment(R.layout.fragment_d) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.backToBButton).setOnClickListener {
            (requireActivity() as? NavigationListener)?.onFragmentDBackBackToB()
        }
    }

    interface NavigationListener {
        fun onFragmentDBackBackToB()
    }

    companion object {

        const val FRAGMENT_D_TAG = "FRAGMENT_D_TAG"

        fun newInstance() = FragmentD()
    }
}