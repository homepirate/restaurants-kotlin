package com.example.restaurents

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.restaurents.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    // Using view binding to avoid null pointer exceptions
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(binding.searchFieldContainer.id, SearchFieldFragment()) // Ensure this matches your layout ID
            .commitNow()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear binding reference to prevent memory leaks
    }
}
