package com.example.firebase.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.firebase.utils.Repository

class UserListViewModel(application: Application) : AndroidViewModel(application) {
    val userList = Repository.getUserList()
}
