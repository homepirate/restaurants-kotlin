package com.example.restaurents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.restaurants.R


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backToStartButton: Button = view.findViewById(R.id.back_to_start_button)
        val logInButton: Button = view.findViewById(R.id.log_in_button)

        logInButton.setOnClickListener {
            (activity as MainActivity).showFragment(SearchFragment::class.java)
        }

        backToStartButton.setOnClickListener {
            (activity as MainActivity).showFragment(LoginRegisterFragment::class.java)
        }
    }
}
