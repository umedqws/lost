package com.example.myapplication

import androidx.recyclerview.widget.DiffUtil

class DiffUtils: DiffUtil.ItemCallback<Lost>(){

    override fun areItemsTheSame(oldItem: Lost, newItem: Lost): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Lost, newItem: Lost): Boolean {
        return oldItem == newItem
    }

}