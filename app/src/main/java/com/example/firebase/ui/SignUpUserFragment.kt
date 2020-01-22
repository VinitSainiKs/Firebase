package com.example.firebase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.firebase.R
import com.example.firebase.databinding.FragmentSignupUserBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class SignUpUserFragment : Fragment() {

    lateinit var mAuth: FirebaseAuth
    lateinit var username: String
    lateinit var password: String
    lateinit var confirmPassword: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSignupUserBinding = FragmentSignupUserBinding.inflate(inflater)
        mAuth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener{
            binding.btnSignUp.startAnimation()
            username = binding.tvUsername.text.toString()
            password = binding.tvPassword.text.toString()
            confirmPassword = binding.tvConfirmPassword.text.toString()
            if (confirmPassword == password){
                try {
                    mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener {
                        binding.btnSignUp.revertAnimation {
                            if (it.isSuccessful){
                                binding.btnSignUp.background = resources.getDrawable(
                                    R.drawable.bg_button
                                )
                                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                                findNavController().navigate(R.id.loginUserFragment)
                            }
                            else{
                                binding.btnSignUp.background = resources.getDrawable(
                                    R.drawable.bg_button
                                )
                                Toast.makeText(context, "${it.result}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }catch (e: Exception){
                    binding.btnSignUp.revertAnimation{
                        binding.btnSignUp.background = resources.getDrawable(R.drawable.bg_button)
                        Toast.makeText(context, "${e.printStackTrace()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                binding.btnSignUp.revertAnimation {
                    binding.btnSignUp.background = resources.getDrawable(R.drawable.bg_button)
                    Toast.makeText(context, "Password and Confirm Password are not same", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }
}