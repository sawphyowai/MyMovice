package com.example.mymovice.database

import androidx.room.*
import com.example.mymovice.domain.DomainImage

@Entity
data class Move constructor(
    @PrimaryKey
    val id :Int,
    val name:String,
    val image:String,
    val summary:String
)

fun List<Move>.asDomainModel(): List<DomainImage> {
    return map {
        DomainImage(
            id = it.id,
            name = it.name,
            image = it.image,
            summary = it.summary
        )
    }
}
