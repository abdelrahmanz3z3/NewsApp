package com.example.news_app.ui.home.newsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.domain.model.News
import com.example.news_app.databinding.ActivityItemNewsBinding

class NewsAdapter(var list: List<News?>?) : Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(val itemBinding: ActivityItemNewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemBinding =
            ActivityItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = list?.size ?: 0
    fun bindNews(list: List<News?>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.item = list!![position]
        holder.itemBinding.executePendingBindings()
        //-----------------------------------------------------
        onItemClickListener?.let { on ->
            holder.itemBinding.root.setOnClickListener {
                on.onClick(position, list!![position]!!)
            }
        }
    }

    var onItemClickListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onClick(position: Int, item: News)
    }
}