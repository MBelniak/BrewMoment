package com.rubik.brewmoment.ui.results

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rubik.brewmoment.MainActivity
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.BrewResultsDAO
import com.rubik.brewmoment.model.data.RecipesDAO
import kotlinx.android.synthetic.main.activity_brew_result.*

class BrewResultActivity : AppCompatActivity() {

    private var seconds = 0
    private var minutes = 0
    private lateinit var recipeKey: String
    private var isDefault: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brew_result)

        val actionBar = supportActionBar
        actionBar?.title = resources.getString(R.string.result_title)
        initLateinits(intent)
        total_brew_time.text = getString(R.string.total_brew_time, getTime())

        save_button.setOnClickListener {
            saveResult()
        }

        save_and_share_button.setOnClickListener{
            saveAndShareResult()
        }
    }

    private fun initLateinits(intent: Intent) {
        recipeKey = intent.extras?.getString("RecipeKey").toString()
        minutes = intent.extras?.getInt("Minutes") ?: 0
        seconds = intent.extras?.getInt("Seconds") ?: 0
        isDefault = intent.extras?.getBoolean("IsDefault") ?: true
    }

    private fun getTime(): String {
        if (seconds < 10)
            return "${minutes}:0${seconds}"
        return "${minutes}:${seconds}"
    }

    private fun saveResult() {
        val coffee = blend_name.text.toString()
        val notes = notes_edit_text.text.toString()
        val saveAsFavourites = save_as_favourite.isChecked
        BrewResultsDAO.saveResult(coffee, notes, saveAsFavourites, recipeKey, isDefault, false)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveAndShareResult() {
        val coffee = blend_name.text.toString()
        val notes = notes_edit_text.text.toString()
        val saveAsFavourites = save_as_favourite.isChecked
        BrewResultsDAO.saveResult(coffee, notes, saveAsFavourites, recipeKey, isDefault, true)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Warning")
            .setMessage("Are you sure you want to exit to the main screen without saving?")

            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(R.string.yes) { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}
