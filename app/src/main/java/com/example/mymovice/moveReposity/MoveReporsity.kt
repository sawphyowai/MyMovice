package com.example.mymovice.moveReposity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mymovice.database.MoveDatabase
import com.example.mymovice.database.asDomainModel
import com.example.mymovice.domain.DomainImage
import com.example.mymovice.network.MoveApi
import com.example.mymovice.network.asDatabaseModel
import kotlinx.coroutines.*
import java.lang.Exception

class MoveReporsity(private val database: MoveDatabase) {

    private val viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob+Dispatchers.IO)
    var result:Boolean=true

    val allData: LiveData<List<DomainImage>> =
        Transformations.map(database.moveDao().getAll()){
            it.asDomainModel()
        }

    suspend fun refreshMove(){
        coroutineScope.launch {
            result=try{
                val getFromNetwork = MoveApi.retro_service.getProperties().await()
                database.moveDao().insertAll(getFromNetwork.asDatabaseModel())
                true
            }catch (e:Exception){
                Log.d("My_Data",e.toString())
                false
            }
        }
    }
}