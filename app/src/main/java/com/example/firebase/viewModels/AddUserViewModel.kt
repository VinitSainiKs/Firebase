package com.example.firebase.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firebase.model.UserDetails
import com.example.firebase.utils.Repository
import com.google.android.gms.tasks.Task

class AddUserViewModel(application: Application) : AndroidViewModel(application) {
    var name: String = ""
    var email: String = ""
    var mobile: String = ""
    var password: String = ""

    fun createUser() {
        Repository.createUser(email, password)
    }

    fun insertUser(): Task<Void> {
        val userDetails = UserDetails(name, email, mobile, password)
        return Repository.insertUserDetails(userDetails, mobile)
    }
}