package com.example.fragmentpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fragmentpractice.UsersAdapter.UserViewHolder
import com.example.fragmentpractice.databinding.ItemUserBinding

class UsersAdapter(private val listener: UserListFragment.UserClickListener) :
    ListAdapter<UserItem, UserViewHolder>(UsersDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)

        holder.item = item

        with(holder.binding) {
            Glide.with(avatar).load(item.avatar).centerCrop().into(avatar)

            name.text = item.name
            phoneNumber.text = item.phoneNumber
        }
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var item: UserItem? = null

        init {
            binding.root.setOnClickListener {
                item?.let { item -> listener.onUserClick(item) }
            }
        }
    }
}