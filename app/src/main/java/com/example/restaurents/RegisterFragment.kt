package com.example.restaurents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.restaurants.R


class RegisterFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backButton: Button = view.findViewById(R.id.back_to_first)
        val registerButton: Button = view.findViewById(R.id.reg_btn)

        backButton.setOnClickListener {
            (activity as MainActivity).showFragment(LoginRegisterFragment::class.java)
        }

        registerButton.setOnClickListener {
//            (activity as MainActivity).showFragment(LoginFragment::class.java)
        }
    }
}