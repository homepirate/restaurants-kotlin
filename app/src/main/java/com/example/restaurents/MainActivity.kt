package com.example.restaurents

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.restaurants.R


class MainActivity : AppCompatActivity() {
    private var navBar: NavBarFragment = NavBarFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_bar_container, navBar)
            .commit()

        if (savedInstanceState == null) {
            showFragment(LoginRegisterFragment::class.java)
        }
    }

    fun showFragment(fragmentClass: Class<out Fragment>) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentManager.fragments.forEach {
            println(it)
            if (it::class.java != NavBarFragment::class.java) {
                fragmentTransaction.hide(it)
            }
        }

        if (fragmentClass == LoginRegisterFragment::class.java) {
            findViewById<View>(R.id.nav_bar_container).visibility = View.GONE
        } else {
            findViewById<View>(R.id.nav_bar_container).visibility = View.VISIBLE

        }

        val tag = fragmentClass.simpleName
        var fragment = fragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = fragmentClass.newInstance()
            fragmentTransaction.add(R.id.fragment_container, fragment, tag)
        } else {
            fragmentTransaction.show(fragment)
        }

        fragmentTransaction.commit()
    }
}
