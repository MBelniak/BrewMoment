package com.rubik.brewmoment.ui.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.rubik.brewmoment.R
import com.rubik.brewmoment.data.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesRecycleViewAdapter(private val recipesDataset: List<Recipe>)
    : RecyclerView.Adapter<RecipesRecycleViewAdapter.RecipesViewHolder>() {

    private var listener: OnItemClickListener? = null

    inner class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var recipeName: TextView = itemView.recipe_name
        var recipeAuthor: TextView = itemView.recipe_author
        var brewingTime: TextView = itemView.brewing_time
        fun bind(recipe: Recipe) {
            recipeName.text = recipe.name
            recipeAuthor.text = recipe.author
            brewingTime.text = recipe.brewTimeToString()
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecipesViewHolder {
        // create a new view
        val recipeData = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item, parent, false) as LinearLayout

        return RecipesViewHolder(recipeData)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(recipesDataset[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipesDataset.size

    fun getItem(position: Int): Recipe {
        return recipesDataset[position]
    }

    interface OnItemClickListener {
        fun onItemClick(recipe: Recipe)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
