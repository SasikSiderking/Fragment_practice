package com.example.fragmentpractice

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.fragmentpractice.UserEditFragment.Companion.USER_EDIT_FRAGMENT_TAG
import com.example.fragmentpractice.UserListFragment.Companion.USER_LIST_FRAGMENT_TAG
import com.example.fragmentpractice.UserListFragment.Companion.newArgumentsBundle
import com.github.javafaker.Faker

class MainActivity : AppCompatActivity(), UserListFragment.UserClickListener,
    UserEditFragment.OkButtonClickListener {

    private var items = with(Faker.instance()) {
        (1..4).map {
            UserItem(
                id = it,
                avatar = this.country().flag(),
                name = this.name().firstName(),
                phoneNumber = this.phoneNumber().cellPhone()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val savedList = savedInstanceState?.parcelableArrayList<UserItem>(LIST_KEY)
        if (savedList != null) {
            items = savedList
        }

        if (supportFragmentManager.findFragmentByTag(USER_LIST_FRAGMENT_TAG) == null) {
            openUsersListFragment()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(LIST_KEY, items as ArrayList<out Parcelable>)
    }

    override fun onUserClick(user: UserItem) {
        openUserEditFragment(user)
    }

    override fun onOkButtonClick(user: UserItem) {
        val currentItems = items.toMutableList()

        with(currentItems) {
            this[indexOf(find { userItem -> userItem.id == user.id })] = user
        }

        items = currentItems

        supportFragmentManager.popBackStackImmediate()
        val listFragment = supportFragmentManager.findFragmentByTag(USER_LIST_FRAGMENT_TAG)
        listFragment?.apply {
            arguments = newArgumentsBundle(items)
            view?.let { onViewCreated(it, null) }
        }
    }

    private fun openUsersListFragment() {
        with(supportFragmentManager) {
            commit {
                replace(
                    R.id.container,
                    UserListFragment.newInstance(items),
                    USER_LIST_FRAGMENT_TAG
                )
            }
        }
    }

    private fun openUserEditFragment(user: UserItem) {
        with(supportFragmentManager) {
            commit {
                replace(
                    R.id.container,
                    UserEditFragment.newInstance(user),
                    USER_EDIT_FRAGMENT_TAG
                )
                addToBackStack(USER_EDIT_FRAGMENT_TAG)
            }
        }
    }

    companion object {
        const val LIST_KEY = "LIST_KEY"
    }
}