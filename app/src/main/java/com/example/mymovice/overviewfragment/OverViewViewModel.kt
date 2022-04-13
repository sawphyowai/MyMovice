package com.example.mymovice.overviewfragment

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.mymovice.database.getInstance
import com.example.mymovice.domain.DomainImage
import com.example.mymovice.moveReposity.MoveReporsity
import kotlinx.coroutines.*

class OverViewViewModel(application: Application):AndroidViewModel(application) {

    private var _allData=MutableLiveData<List<DomainImage>>()
    val allData:LiveData<List<DomainImage>>
    get() = _allData

    private val _navigatedToMoveDetail=MutableLiveData<DomainImage?>()
    val navigatedToMoveDetail:LiveData<DomainImage?>
    get() = _navigatedToMoveDetail

    private val viewModelJob= Job()
    private val coroutineScope= CoroutineScope(viewModelJob+Dispatchers.Main)

    private val database= getInstance(application)
    private val reposity=MoveReporsity(database)

    init{
        viewModelScope.launch {
            reposity.refreshMove()
        }
        _allData=reposity.allData as MutableLiveData<List<DomainImage>>
    }
    val result=MoveReporsity(database).result

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
    fun navigatedToDetail(moveProperty: DomainImage){
        _navigatedToMoveDetail.value=moveProperty
    }
    fun completeNavigation(){
        _navigatedToMoveDetail.value=null
    }
}