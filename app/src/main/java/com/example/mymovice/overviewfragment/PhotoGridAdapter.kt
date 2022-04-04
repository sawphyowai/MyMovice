package com.example.mymovice.overviewfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovice.databinding.GridViewItemBinding
import com.example.mymovice.network.MoveProperty

class PhotoGridAdapter(val clickListener:ClickListener):ListAdapter<MoveProperty,PhotoGridAdapter.ViewHolder>(CallBack()){
    class ViewHolder(var binding: GridViewItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:MoveProperty?){
            binding.move=item
            binding.executePendingBindings()
        }
    }

    class ClickListener(val clickListener:(moveData:MoveProperty)->Unit) {
        fun onClick(moveData:MoveProperty)=clickListener(moveData)
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

class CallBack :DiffUtil.ItemCallback<MoveProperty>(){
    override fun areItemsTheSame(oldItem: MoveProperty, newItem: MoveProperty): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: MoveProperty, newItem: MoveProperty): Boolean {
        return oldItem.id==newItem.id
    }
}
