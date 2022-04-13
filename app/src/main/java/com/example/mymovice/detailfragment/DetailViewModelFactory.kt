package com.example.mymovice.detailfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovice.domain.DomainImage
import com.example.mymovice.network.MoveProperty

class DetailViewModelFactory(val moveProperty: DomainImage,val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(moveProperty,application) as T
        }
        throw IllegalAccessException("Unknown ViewModel")
    }
}