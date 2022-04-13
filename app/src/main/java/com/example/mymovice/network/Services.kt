package com.example.mymovice.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.tvmaze.com/"

private var moshi=Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private var retrofit= Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface Service {
    @GET("shows")
    fun getProperties(): Deferred<List<MoveProperty>>
}

object MoveApi {
    val retro_service: Service by lazy {
        retrofit.create(Service::class.java)
    }
}
