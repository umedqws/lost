package com.example.myapplication

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemsTextBinding

class MyAdapter : ListAdapter<Lost, MyAdapter.MyViewHolder>(DiffUtils()) {
    var OnClickItem: ((Lost) -> Unit)? = null

   inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        private val binding = ItemsTextBinding.bind(itemView)

       init {
           itemView.setOnClickListener {
               Log.d("TAG_TEST", ": ${getItem(adapterPosition).img}")
               OnClickItem?.invoke(getItem(adapterPosition))
           }
       }


        fun bind(lost: Lost) {
            binding.name.text = lost.name
            binding.description.text = lost.discription
            binding.telefonAdd.text = lost.telefon
            //Соединени ImageView с Uri ссылкой
            if (lost.img != null)
                Glide
                    .with(itemView.context)
                    .load(Uri.parse(lost.img))
                    .centerCrop()
                    .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_text, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
