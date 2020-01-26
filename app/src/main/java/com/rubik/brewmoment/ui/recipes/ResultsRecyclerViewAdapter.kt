package com.rubik.brewmoment.ui.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.BrewResult
import kotlinx.android.synthetic.main.result_item.view.*

class ResultsRecyclerViewAdapter(private val resultsDataset: List<BrewResult>, val context: Context)
    : RecyclerView.Adapter<ResultsRecyclerViewAdapter.ResultsViewHolder>() {

    private lateinit var listener: OnResultItemClickListener

    inner class ResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position))
                }
            }
        }

        private var resultBlend: TextView = itemView.result_blend
        private var resultDate: TextView = itemView.result_date
        private var isFavourite: ImageView = itemView.favourite_star
        fun bind(brewResult: BrewResult) {
            resultBlend.text = brewResult.coffeeBlend
            resultDate.text = brewResult.date.toString()
            isFavourite.visibility = if (brewResult.isFavourite) View.VISIBLE else View.INVISIBLE
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ResultsViewHolder {
        // create a new view
        val resultData = LayoutInflater.from(parent.context)
            .inflate(R.layout.result_item, parent, false) as LinearLayout
        return ResultsViewHolder(resultData)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(resultsDataset[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = resultsDataset.size

    fun getItem(position: Int): BrewResult {
        return resultsDataset[position]
    }

    fun setOnItemClickListener(listener: OnResultItemClickListener) {
        this.listener = listener
    }
}
interface OnResultItemClickListener {
    fun onItemClick(brewResult: BrewResult)
}
