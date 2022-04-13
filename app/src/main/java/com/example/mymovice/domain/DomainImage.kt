package com.example.mymovice.domain

import android.os.Parcelable
import com.example.mymovice.network.Image
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainImage(
    val id :Int,
    val name:String,
    val image: String,
    val summary:String
):Parcelable
