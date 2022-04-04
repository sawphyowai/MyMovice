package com.example.mymovice.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class MoveContainer(val move:List<MoveProperty>)

@Parcelize
@JsonClass(generateAdapter = true)
data class MoveProperty(
    val id :Int,
    val name:String,
    @Json(name="image")
    val image:Image,
    val summary:String
):Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "original")
    val original:String,

):Parcelable



//fun MoveContainer.asDomainModel():Array<DatabaseImage>{
//    return move.map{
//        DatabaseImage(
//            id = it.id,
//            name = it.name,
//            image = it.image,
//            summary = it.summary
//        )
//    }.toTypedArray()
//}
//
//fun MoveContainer.asDatabaseModel():List<DomainImage>{
//    return move.map {
//        DomainImage(
//            id = it.id,
//            name = it.name,
//            image = it.image,
//            summary = it.summary
//        )
//    }
//}