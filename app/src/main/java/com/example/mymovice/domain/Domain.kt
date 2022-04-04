package com.example.mymovice.domain

import com.example.mymovice.network.Image

data class DomainImage(
    val id :Int,
    val name:String,
    val image: Image,
    val summary:String
)

