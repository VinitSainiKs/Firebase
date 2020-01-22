package com.example.firebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.firebase.R
import com.example.firebase.databinding.FragmentAddUserBinding
import com.example.firebase.viewModels.AddUserViewModel
import java.util.*

class AddUserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddUserBinding.inflate(inflater)
        val viewModel: AddUserViewModel by viewModels()
        binding.viewModel = viewModel

        binding.btnAddUser.setOnClickListener {
            binding.btnAddUser.startAnimation()
            viewModel.createUser()
            viewModel.insertUser().addOnCompleteListener {
                binding.btnAddUser.revertAnimation {
                    binding.btnAddUser.background = resources.getDrawable(R.drawable.bg_button)
                    if (it.isSuccessful)
                        Toast.makeText(context, "User Added Successfully", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }
}