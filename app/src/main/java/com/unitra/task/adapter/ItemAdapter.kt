package com.unitra.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.unitra.task.R
import com.unitra.task.callbacks.ClickListener
import com.unitra.task.models.ItemModel



class ItemAdapter(private val list: List<ItemModel>,private val listener: ClickListener):
    RecyclerView.Adapter<ItemAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val model: ItemModel = list[position]
        holder.bind(model)


        holder.cardview?.setOnClickListener{
            listener.onClickListener(model.lock);

        }

    }


    override fun getItemCount(): Int = list.size


    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list, parent, false)) {

        private var mTitleView: TextView? = null
        private var mLockView: TextView? = null
        public var cardview:CardView? = null


        init {
            mTitleView = itemView.findViewById(R.id.text_view)
            mLockView = itemView.findViewById(R.id.text_lock)
            cardview = itemView.findViewById(R.id.card_view);
        }

        fun bind(movie: ItemModel) {
            mTitleView?.text = movie.name

            if(movie.lock==1){
                mLockView?.text = "unlock"
            }
            else{
                mLockView?.text = "lock"
            }

        }
    }
}