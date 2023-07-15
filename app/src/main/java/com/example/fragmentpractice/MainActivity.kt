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
            with(FragmentB) {
                replace(R.id.fragmentContainer, newInstance(), FRAGMENT_B_TAG)
                addToBackStack(FRAGMENT_B_TAG)
            }
        }
    }

    override fun onFragmentBNext(helloText: String) {
        supportFragmentManager.commit {
            with(FragmentC) {
                replace(
                    R.id.fragmentContainer,
                    newInstance(helloText),
                    FRAGMENT_C_TAG
                )
                addToBackStack(FRAGMENT_C_TAG)
            }
        }
    }

    override fun onFragmentBBack() {
        supportFragmentManager.popBackStack()
    }

    override fun onFragmentCNext() {
        supportFragmentManager.commit {
            with(FragmentD) {
                replace(R.id.fragmentContainer, newInstance(), FRAGMENT_D_TAG)
                addToBackStack(FRAGMENT_D_TAG)
            }
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