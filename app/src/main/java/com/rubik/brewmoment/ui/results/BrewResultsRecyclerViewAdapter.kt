package com.rubik.brewmoment.ui.results

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.model.data.RecipesDAO
import kotlinx.android.synthetic.main.result_item.view.*
import java.text.SimpleDateFormat
import java.util.*


class BrewResultsRecyclerViewAdapter(var resultsDataset: List<BrewResult>, val context: Context)
    : RecyclerView.Adapter<BrewResultsRecyclerViewAdapter.ResultsViewHolder>() {

    private lateinit var listener: OnResultItemClickListener
    var showFavourites: Boolean = true

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
        private var author: TextView = itemView.result_author
        private var isFavourite: ImageView = itemView.favourite_star
        private var eqImage: ImageView = itemView.eq_image
        fun bind(brewResult: BrewResult) {
            resultBlend.text = brewResult.coffeeBlend
            resultDate.text = getDate(brewResult.date, "dd-MM-yyyy")
            author.text = brewResult.author
            eqImage.setImageResource(
                when(brewResult.equipment) {
                    EqTypeEnum.AEROPRESS -> R.mipmap.ic_aeropress_icon_foreground
                    EqTypeEnum.DRIP -> R.mipmap.ic_drip_icon_foreground
                    EqTypeEnum.CHEMEX -> R.mipmap.ic_chemex_foreground
                    EqTypeEnum.FRENCH_PRESS -> R.mipmap.ic_chemex_foreground
                }
            )
            if (showFavourites && brewResult.isFavourite)
                isFavourite.visibility = View.VISIBLE
            else isFavourite.visibility =  View.INVISIBLE
        }

        private fun getDate(milliSeconds: Long, dateFormat: String): String {
            // Create a DateFormatter object for displaying date in specified format.
            val formatter = SimpleDateFormat(dateFormat)

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
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
