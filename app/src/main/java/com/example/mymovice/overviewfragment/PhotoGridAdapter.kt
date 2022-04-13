package com.example.mymovice.overviewfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovice.databinding.GridViewItemBinding
import com.example.mymovice.domain.DomainImage
import com.example.mymovice.network.MoveProperty

class PhotoGridAdapter(val clickListener:ClickListener):ListAdapter<DomainImage,PhotoGridAdapter.ViewHolder>(CallBack()){
    class ViewHolder(var binding: GridViewItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:DomainImage?){
            binding.move=item
            binding.executePendingBindings()
        }
    }

    class ClickListener(val clickListener:(moveData:DomainImage)->Unit) {
        fun onClick(moveData:DomainImage)=clickListener(moveData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener.onClick(item)
        }
    }

}

class CallBack :DiffUtil.ItemCallback<DomainImage>(){
    override fun areItemsTheSame(oldItem: DomainImage, newItem: DomainImage): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: DomainImage, newItem: DomainImage): Boolean {
        return oldItem.id==newItem.id
    }

}
