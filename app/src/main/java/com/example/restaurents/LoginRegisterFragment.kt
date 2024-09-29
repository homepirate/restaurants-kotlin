package com.example.restaurents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class LoginRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Раздуваем макет фрагмента
        return inflater.inflate(R.layout.fragment_login_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Найти кнопки
        val loginButton: Button = view.findViewById(R.id.login_button)
        val registerButton: Button = view.findViewById(R.id.register_button)

        // Установить слушатели кликов для кнопок
        loginButton.setOnClickListener {
            // Открыть фрагмент входа
            (activity as MainActivity).showFragment(SearchFragment::class.java)
            println("Open login")
        }

        registerButton.setOnClickListener {
            // Открыть фрагмент регистрации
//            (activity as MainActivity).showFragment(RegisterFragment::class.java)
            println("Open registration")
        }
    }
}
