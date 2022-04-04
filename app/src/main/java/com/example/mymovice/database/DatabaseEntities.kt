//package com.example.mymovice.database
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.example.mymovice.domain.DomainImage
//import com.example.mymovice.network.Image
//
//@Entity
//data class DatabaseImage constructor(
//    @PrimaryKey
//    val id :Int,
//    val name:String,
//    val image:Image,
//    val summary:String
//)
//fun List<DatabaseImage>.asDomainModel():List<DomainImage>{
//    return map{
//        DomainImage(
//            id = it.id,
//            name = it.name,
//            image = it.image,
//            summary = it.summary
//        )
//    }
//}