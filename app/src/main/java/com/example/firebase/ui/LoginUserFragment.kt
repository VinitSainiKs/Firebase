package com.example.firebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.firebase.R
import com.example.firebase.databinding.FragmentLoginUserBinding
import com.google.firebase.auth.FirebaseAuth

class LoginUserFragment : Fragment() {

    lateinit var mAuth: FirebaseAuth
    lateinit var username: String
    lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginUserBinding = FragmentLoginUserBinding.inflate(inflater)

        mAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.startAnimation()
            username = binding.tvUsername.text.toString()
            binding.tvUsername.bottom
            password = binding.tvPassword.text.toString()
            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
                binding.btnLogin.revertAnimation {
                    if(it.isSuccessful){
                        binding.btnLogin.background = resources.getDrawable(R.drawable.bg_button)
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.addUserFragment)
                    }
                    else{
                        binding.btnLogin.background = resources.getDrawable(R.drawable.bg_button)
                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.tvSignUpHere.setOnClickListener {
            findNavController().navigate(R.id.signUpUserFragment)
        }
        return binding.root
    }
}