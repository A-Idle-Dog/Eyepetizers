package com.example.module_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module_home.R

class footerAdapter(val retry: () -> Unit) : LoadStateAdapter<footerAdapter.footerViewHolder>() {
    override fun onBindViewHolder(holder: footerViewHolder, loadState: LoadState) {
        holder.text.isVisible = loadState is LoadState.Error
        holder.par.isVisible = loadState is LoadState.Loading
        holder.text.setOnClickListener {
            retry()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): footerViewHolder {
        val view  =LayoutInflater.from(parent.context).inflate(R.layout.footer_item,parent,false)
        return footerViewHolder(view)
    }

    inner class footerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val text : TextView =itemView.findViewById(R.id.retry)
        val par : ProgressBar = itemView.findViewById(R.id.progress_bar)
    }
}