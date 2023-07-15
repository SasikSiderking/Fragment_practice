package com.example.fragmentpractice

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Bundle.parcelableArrayList(key: String): ArrayList<T>? = when {
    SDK_INT >= SDK_INT_FOR_UNDEPRECATED_GET -> getParcelableArrayList(key, T::class.java)
    else -> getParcelableArrayList(key)
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= SDK_INT_FOR_UNDEPRECATED_GET -> getParcelable(key, T::class.java)
    else -> getParcelable(key)
}

const val SDK_INT_FOR_UNDEPRECATED_GET = 33

