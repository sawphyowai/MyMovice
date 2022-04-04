package com.example.mymovice

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovice.network.MoveApi
import com.example.mymovice.network.MoveProperty
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}