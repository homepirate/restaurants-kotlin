package com.example.restaurents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.restaurants.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editProfileButton: Button = view.findViewById(R.id.edit_profile_button)
        val logoutButton: Button = view.findViewById(R.id.logout_button)
        val profileName: TextView = view.findViewById(R.id.profile_name)

        editProfileButton.setOnClickListener {

        }

        logoutButton.setOnClickListener {
            (activity as MainActivity).showFragment(LoginRegisterFragment::class.java)
        }
    }
}
