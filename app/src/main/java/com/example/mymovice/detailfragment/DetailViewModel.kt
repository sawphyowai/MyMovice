package com.example.mymovice.detailfragment

import android.app.Application
import android.os.Build
import android.text.Html
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mymovice.network.MoveProperty

class DetailViewModel(val moveProperty: MoveProperty,val application: Application):ViewModel() {

    private val _selectedMoveProperty= MutableLiveData<MoveProperty>()
    val selectedMoveProperty: LiveData<MoveProperty>
        get() = _selectedMoveProperty
    init{
        _selectedMoveProperty.value= moveProperty
    }

    var displaySummery=Transformations.map(selectedMoveProperty){
        var str:String=it.summary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(str).toString()
        }
    }

}