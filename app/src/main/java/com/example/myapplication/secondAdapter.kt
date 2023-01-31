package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ItemsCategoryBinding

class SecondAdapter:ListAdapter<Categor,SecondAdapter.TopViewHolder>(SecondDiffUtils()) {
    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_category,parent,false)
        return TopViewHolder(view)
    }
    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class TopViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        private val binding = ItemsCategoryBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }
        fun bind(categor: Categor){
            binding.categoryName.text = categor.name
            if (categor.isSelected){
                binding.categoryName.setBackgroundColor(itemView.context.resources.getColor(R.color.click))
            }else{
                binding.categoryName.setBackgroundColor(itemView.context.resources.getColor(R.color.back))
            }
        }
    }
}
class SecondDiffUtils: DiffUtil.ItemCallback<Categor>(){
    override fun areItemsTheSame(oldItem: Categor, newItem: Categor): Boolean {
       return oldItem.Id == newItem.Id
    }

    override fun areContentsTheSame(oldItem: Categor, newItem: Categor):Boolean {
        return oldItem == newItem
    }


}
