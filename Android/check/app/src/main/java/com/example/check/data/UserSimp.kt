package com.example.check.data

import android.os.Parcel
import android.os.Parcelable

data class UserSimp(var nome:String?, val email:String?, val senha:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<UserSimp> {
        override fun createFromParcel(parcel: Parcel): UserSimp {
            return UserSimp(parcel)
        }

        override fun newArray(size: Int): Array<UserSimp?> {
            return arrayOfNulls(size)
        }
    }
}