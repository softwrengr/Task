package com.unitra.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unitra.task.R
import com.unitra.task.models.ItemModel
import kotlinx.android.synthetic.main.item_list.view.*

class ItemAdapter(private val list: List<ItemModel>):
    RecyclerView.Adapter<ItemAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: ItemModel = list[position]
        holder.bind(movie)

        holder.itemView.card_view.setOnClickListener{

        }

    }


    override fun getItemCount(): Int = list.size


    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list, parent, false)) {
        private var mTitleView: TextView? = null


        init {
            mTitleView = itemView.findViewById(R.id.text_view)
        }

        fun bind(movie: ItemModel) {
            mTitleView?.text = movie.name
        }

    }

    interface ClickListener {
        fun onClickListener(item:String)
    }

}