package com.example.restaurents

import android.os.Bundle
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

        if (savedInstanceState == null) {
//            showFragment(NavBarFragment::class.java)
            showFragment(LoginRegisterFragment::class.java)
//            showFragment(SearchFragment::class.java)
        }
    }

    public fun showFragment(fragmentClass: Class<out Fragment>) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentManager.fragments.forEach {
            fragmentTransaction.hide(it)
        }

        val tag = fragmentClass.simpleName
        var fragment = fragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = fragmentClass.newInstance()
            fragmentTransaction.add(R.id.fragment_container, fragment, tag)
        } else {
            fragmentTransaction.show(fragment)
        }

        fragmentTransaction.commitNow()
    }
}