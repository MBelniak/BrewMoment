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
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.EqTypeEnum
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesRecyclerViewAdapter(private val recipesDataset: List<Recipe>, val context: Context)
    : RecyclerView.Adapter<RecipesRecyclerViewAdapter.RecipesViewHolder>() {

    private lateinit var listener: OnItemClickListener

    inner class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position))
                }
            }
        }

        private var recipeName: TextView = itemView.recipe_name
        private var recipeAuthor: TextView = itemView.recipe_author
        private var brewingTime: TextView = itemView.brewing_time
        private var image: ImageView = itemView.image
        fun bind(recipe: Recipe) {
            recipeName.text = recipe.title
            recipeAuthor.text = context.getString(R.string.author, recipe.author)
            brewingTime.text = context.getString(R.string.brew_time, recipe.brewTimeToString())
            when (recipe.equipment) {
                EqTypeEnum.AEROPRESS -> image.setImageResource(R.mipmap.ic_aeropress_icon)
                EqTypeEnum.DRIP -> image.setImageResource(R.mipmap.ic_drip_icon)
                EqTypeEnum.CHEMEX -> image.setImageResource(R.mipmap.ic_chemex_foreground)
                EqTypeEnum.FRENCH_PRESS -> image.setImageResource(R.mipmap.ic_french_icon)
            }
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
        holder.bind(recipesDataset[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = recipesDataset.size

    fun getItem(position: Int): Recipe {
        return recipesDataset[position]
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
interface OnItemClickListener {
    fun onItemClick(recipe: Recipe)
}
