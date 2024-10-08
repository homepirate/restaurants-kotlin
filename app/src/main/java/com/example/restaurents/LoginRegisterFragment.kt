package com.example.restaurents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.restaurants.R

class LoginRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goToLoginButton: Button = view.findViewById(R.id.go_to_login_button)
        val goToRegisterButton: Button = view.findViewById(R.id.go_to_register_button)

        goToLoginButton.setOnClickListener {
            (activity as MainActivity).showFragment(LoginFragment::class.java)
        }

        goToRegisterButton.setOnClickListener {
            (activity as MainActivity).showFragment(RegisterFragment::class.java)
        }
    }
}
