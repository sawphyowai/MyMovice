package com.example.mymovice.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import com.example.mymovice.domain.DomainImage
import com.example.mymovice.database.*

//@JsonClass(generateAdapter = true)
//data class NetworkMoveContainer(val move:List<MoveProperty>)

@JsonClass(generateAdapter = true)
data class MoveProperty(
    val id :Int,
    val name:String,
    @Json(name="image")
    val image:Image,
    val summary:String
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Image(
    @Json(name = "original")
    val original:String?,
    ):Parcelable

fun List<MoveProperty>.asDomainModel():List<DomainImage>{
    return map{
        DomainImage(
            id = it.id,
            name = it.name,
            image = it.image.original.toString(),
            summary = it.summary
        )
    }
}

fun List<MoveProperty>.asDatabaseModel():List<Move>{
    return map {
        Move(
            id = it.id,
            name = it.name,
            image = it.image.original.toString(),
            summary = it.summary
        )
    }
}