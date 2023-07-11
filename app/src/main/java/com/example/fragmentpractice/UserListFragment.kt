package com.example.fragmentpractice

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = arguments?.parcelableArrayList<UserItem>(USER_LIST_EXTRA)

        view.findViewById<RecyclerView>(R.id.usersRecyclerView).adapter =
            (requireActivity() as? UserClickListener)?.let {
                UsersAdapter(it).apply { submitList(items) }
            }
    }

    interface UserClickListener {
        fun onUserClick(user: UserItem)
    }

    companion object {

        const val USER_LIST_FRAGMENT_TAG = "USER_LIST_FRAGMENT_TAG"

        private const val USER_LIST_EXTRA = "USER_LIST_EXTRA"

        fun newInstance(list: List<UserItem>) = UserListFragment().apply {
            arguments = bundleOf(USER_LIST_EXTRA to list)
        }

        fun newArgumentsBundle(list: List<UserItem>) = bundleOf(USER_LIST_EXTRA to list)
    }
}