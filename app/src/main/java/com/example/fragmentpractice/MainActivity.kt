package com.example.fragmentpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), FragmentA.NavigationListener,
    FragmentB.NavigationListener, FragmentC.NavigationListener, FragmentD.NavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onFragmentANext() {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, FragmentB.newInstance(), FragmentB.FRAGMENT_B_TAG)
            addToBackStack(FragmentB.FRAGMENT_B_TAG)
        }
    }

    override fun onFragmentBNext(helloText: String) {
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainer,
                FragmentC.newInstance(helloText),
                FragmentC.FRAGMENT_C_TAG
            )
            addToBackStack(FragmentC.FRAGMENT_C_TAG)
        }
    }

    override fun onFragmentBBack() {
        supportFragmentManager.popBackStack()
    }

    override fun onFragmentCNext() {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, FragmentD.newInstance(), FragmentD.FRAGMENT_D_TAG)
            addToBackStack(FragmentD.FRAGMENT_D_TAG)
        }
    }

    override fun onFragmentCBackToA() {
        supportFragmentManager.popBackStack(
            FragmentB.FRAGMENT_B_TAG,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    override fun onFragmentDBackToB() {
        supportFragmentManager.popBackStack(FragmentB.FRAGMENT_B_TAG, 0)
    }
}