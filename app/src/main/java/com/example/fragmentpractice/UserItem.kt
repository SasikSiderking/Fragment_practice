package com.example.fragmentpractice

import android.os.Parcel
import android.os.Parcelable

data class UserItem(
    val id: Int,
    val avatar: String,
    val name: String,
    val phoneNumber: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(avatar)
        parcel.writeString(name)
        parcel.writeString(phoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    private companion object CREATOR : Parcelable.Creator<UserItem> {
        override fun createFromParcel(parcel: Parcel): UserItem {
            return UserItem(parcel)
        }

        override fun newArray(size: Int): Array<UserItem?> {
            return arrayOfNulls(size)
        }
    }
}
