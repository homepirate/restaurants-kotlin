package com.example.restaurents

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

//        val usernameInput: EditText = view.findViewById(R.id.username_input)
//        val emailInput: EditText = view.findViewById(R.id.email_input)
//        val passwordInput: EditText = view.findViewById(R.id.password_input)

        backButton.setOnClickListener {
            (activity as MainActivity).showFragment(LoginRegisterFragment::class.java)
        }

        registerButton.setOnClickListener {
//            (activity as MainActivity).showFragment(LoginFragment::class.java)
        }
    }
}
//        registerButton.setOnClickListener {
//            val username = usernameInput.text.toString().trim()
//            val email = emailInput.text.toString().trim()
//            val password = passwordInput.text.toString().trim()
//
//            if (validateInputs(username, email, password)) {

//                Toast.makeText(requireContext(), "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun validateInputs(username: String, email: String, password: String): Boolean {
//        if (username.isEmpty()) {
//            Toast.makeText(requireContext(), "Введите имя", Toast.LENGTH_SHORT).show()
//            return false
//        }
//
//        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Toast.makeText(requireContext(), "Введите корректный email", Toast.LENGTH_SHORT).show()
//            return false
//        }
//
//        if (password.isEmpty()) {
//            Toast.makeText(requireContext(), "Введите пароль", Toast.LENGTH_SHORT).show()
//            return false
//        }
//
//        return true
//    }
//}
