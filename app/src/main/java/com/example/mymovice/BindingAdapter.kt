package com.example.mymovice

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymovice.domain.DomainImage
import com.example.mymovice.overviewfragment.PhotoGridAdapter

@BindingAdapter("Image")
fun bindingImage(ImageView:ImageView,Img_Url:String?){
        Glide.with(ImageView.context)
            .load(Img_Url)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.ic_broken_image)
            )
            .into(ImageView)
}
@BindingAdapter("listData")
fun bindAdapter(recyclerView: RecyclerView,data:List<DomainImage>?){
    if(data!=null){
        var adapter=recyclerView.adapter as PhotoGridAdapter
        adapter.submitList(data)
    }
}