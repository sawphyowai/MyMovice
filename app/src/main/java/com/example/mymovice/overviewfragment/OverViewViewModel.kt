package com.example.mymovice.overviewfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovice.network.MoveApi
import com.example.mymovice.network.MoveProperty
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

enum class MoveStatus{
    LOADING,ERROR,DONE
}
class OverViewViewModel:ViewModel() {

    private val _response=MutableLiveData<List<MoveProperty>>()
    val response:LiveData<List<MoveProperty>>
    get() = _response

    private val _status= MutableLiveData<MoveStatus>()
    val status:LiveData<MoveStatus>
    get() = _status

    private val _navigatedToMoveDetail=MutableLiveData<MoveProperty>()
    val navigatedToMoveDetail:LiveData<MoveProperty>
    get() = _navigatedToMoveDetail

    private val viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob+Dispatchers.Main)

    init{
        getOverViewNetWork()
    }

    private fun getOverViewNetWork() {
        coroutineScope.launch {
            _status.value=MoveStatus.LOADING
            try{
                val getFromNetwork=MoveApi.retro_service.getProperties().await()
                _response.value=getFromNetwork
                _status.value=MoveStatus.DONE
            }catch (e:Exception){
                _status.value=MoveStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
    fun navigatedToDetail(moveProperty: MoveProperty){
        _navigatedToMoveDetail.value=moveProperty
    }
    fun completeNavigation(){
        _navigatedToMoveDetail.value=null
    }
}