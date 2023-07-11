package com.example.fragmentpractice

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class UserEditFragment : Fragment(R.layout.fragment_user_edit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.parcelable<UserItem>(USER_EDIT_EXTRA)

        val avatarEditText = view.findViewById<EditText>(R.id.avatar)
        val nameEditText = view.findViewById<EditText>(R.id.name)
        val phoneEditText = view.findViewById<EditText>(R.id.phoneNumber)
        val button = view.findViewById<Button>(R.id.okButton)

        if (user != null) {
            avatarEditText.setText(user.avatar)
            nameEditText.setText(user.name)
            phoneEditText.setText(user.phoneNumber)

            button.setOnClickListener {
                (requireActivity() as? OkButtonClickListener)?.onOkButtonClick(
                    user.copy(
                        avatar = avatarEditText.text.toString(),
                        name = nameEditText.text.toString(),
                        phoneNumber = phoneEditText.text.toString()
                    )
                )
            }
        }
    }

    interface OkButtonClickListener {
        fun onOkButtonClick(user: UserItem)
    }

    companion object {
        const val USER_EDIT_FRAGMENT_TAG = "USER_EDIT_FRAGMENT_TAG"

        private const val USER_EDIT_EXTRA = "USER_EDIT_EXTRA"

        fun newInstance(user: UserItem) = UserEditFragment().apply {
            arguments = bundleOf(USER_EDIT_EXTRA to user)
        }
    }
}