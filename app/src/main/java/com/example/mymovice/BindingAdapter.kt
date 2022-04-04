package com.example.mymovice

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymovice.domain.DomainImage
import com.example.mymovice.network.MoveProperty
import com.example.mymovice.overviewfragment.MoveStatus
import com.example.mymovice.overviewfragment.PhotoGridAdapter
import com.google.android.material.tabs.TabLayout

@BindingAdapter("Image")
fun bindingImage(ImageView:ImageView,Img_Url:String?){
    Img_Url.let{
        val url=it?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(ImageView.context)
            .load(url)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.ic_broken_image)
            )
            .into(ImageView)
    }
}
@BindingAdapter("listData")
fun bindAdapter(recyclerView: RecyclerView,data:List<MoveProperty>?){
    if(data!=null){
        var adapter=recyclerView.adapter as PhotoGridAdapter
        adapter.submitList(data)
    }
}
@BindingAdapter("textTitle")
fun TextView.bindTitle(item:MoveProperty?){
    item.let{
        if (item != null) {
            text=item.name
        }
    }
}

@BindingAdapter("ImageStatus")
fun bindImage(statusImage: ImageView,status:MoveStatus?){
    when(status){
        MoveStatus.LOADING ->{
            statusImage.visibility= View.VISIBLE
            statusImage.setImageResource(R.drawable.loading_img)
        }
        MoveStatus.ERROR ->{
            statusImage.visibility=View.VISIBLE
            statusImage.setImageResource(R.drawable.ic_broken_image)
        }
        MoveStatus.DONE->{
            statusImage.visibility=View.GONE
        }
    }
}