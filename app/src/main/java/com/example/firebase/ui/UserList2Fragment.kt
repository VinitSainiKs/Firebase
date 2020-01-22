package com.example.firebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.firebase.databinding.FragmentUserList2Binding
import com.example.firebase.viewModels.UserListViewModel

class UserList2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserList2Binding.inflate(inflater)
        val viewModel: UserListViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.userList.observe(viewLifecycleOwner){
            println("List -->> $it")
        }

        return binding.root
    }
}