package com.example.aula5

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Operation(val expression: String, val result: String) : Parcelable {

    override fun toString(): String {
        return "$expression=$result"
    }
}
