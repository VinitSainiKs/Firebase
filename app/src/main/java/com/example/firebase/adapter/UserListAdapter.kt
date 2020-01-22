package com.example.firebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.CardUserListBinding
import com.example.firebase.model.UserDetails

class UserListAdapter(private val list: List<UserDetails>) : RecyclerView.Adapter<UserListAdapter.UserListHolder>() {
    lateinit var binding: CardUserListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListHolder {
        binding =CardUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.list = list
        return UserListHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserListHolder, position: Int) {
        binding.position = position
    }



    class UserListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}