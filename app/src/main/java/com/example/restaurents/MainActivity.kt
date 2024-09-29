package com.example.restaurents

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Remove the initial call to showFragment
        // if (savedInstanceState == null) {
        //     showFragment(NavBarFragment::class.java)
        // }
    }

    fun showFragment(fragmentClass: Class<out Fragment>) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Logging to see which fragment is being shown
        Log.d("MainActivity", "Showing fragment: ${fragmentClass.simpleName}")

        // Hide all fragments except for NavBarFragment
        fragmentManager.fragments.forEach {
            if (it !is NavBarFragment) {
                fragmentTransaction.hide(it)
            }
        }

        // Determine if we need to create a new instance or reuse an existing one
        val tag = fragmentClass.simpleName
        var fragment = fragmentManager.findFragmentByTag(tag)

        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance()
                fragmentTransaction.add(R.id.fragment_container, fragment, tag)
                Log.d("MainActivity", "Created new instance of: ${fragmentClass.simpleName}")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error creating fragment: ${e.message}")
            }
        } else {
            fragmentTransaction.show(fragment)
            Log.d("MainActivity", "Reusing existing fragment: ${fragmentClass.simpleName}")
        }

        fragmentTransaction.commitAllowingStateLoss()
    }
}