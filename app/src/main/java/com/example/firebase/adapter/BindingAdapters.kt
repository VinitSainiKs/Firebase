package com.example.firebase.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.model.UserDetails

@BindingAdapter("app:user_list")
fun getUserList(recyclerView: RecyclerView, list: List<UserDetails>){
    recyclerView.adapter = UserListAdapter(list)
}