package com.rubik.brewmoment.ui.results

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rubik.brewmoment.MainActivity
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.model.data.BrewResultsDAO
import com.rubik.brewmoment.util.LoginUtil
import kotlinx.android.synthetic.main.activity_brew_result.*

class BrewResultActivity : AppCompatActivity() {

    private var seconds = 0
    private var minutes = 0
    private lateinit var recipeKey: String
    private var isDefault: Boolean = true
    private var equipment: EqTypeEnum = EqTypeEnum.AEROPRESS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brew_result)

        val actionBar = supportActionBar
        actionBar?.title = resources.getString(R.string.result_title)
        initLateinits(intent)
        total_brew_time.text = getString(R.string.total_brew_time, getTime())
    }

    override fun onStart() {
        super.onStart()

        if (LoginUtil.isUserLoggedIn()) {
            logged_in_lin_layout.visibility = View.VISIBLE
            save_and_share_button.visibility = View.VISIBLE
            save_button.visibility = View.VISIBLE
            save_button.setOnClickListener {
                saveResult()
            }

            save_and_share_button.setOnClickListener {
                saveAndShareResult()
            }
        }
        else
        {
            save_and_share_button.visibility = View.GONE
            save_button.visibility = View.GONE
            logged_in_lin_layout.visibility = View.GONE
            exit_button.visibility = View.VISIBLE
            exit_button.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun initLateinits(intent: Intent) {
        recipeKey = intent.extras?.getString("RecipeKey").toString()
        minutes = intent.extras?.getInt("Minutes") ?: 0
        seconds = intent.extras?.getInt("Seconds") ?: 0
        isDefault = intent.extras?.getBoolean("IsDefault") ?: true
        equipment = EqTypeEnum.values()[intent.extras?.getInt("EqType") ?: 0]
    }

    private fun getTime(): String {
        if (seconds < 10)
            return "${minutes}:0${seconds}"
        return "${minutes}:${seconds}"
    }

    private fun saveResult() {
        if (blend_name.text.isBlank())
        {
            blend_name.error = "This field is mandatory"
            return
        }
        val notes = notes_edit_text.text.toString()
        val coffee = blend_name.error.toString()
        val saveAsFavourites = save_as_favourite.isChecked
        val user = LoginUtil.getCurrentUser()
        if (user != null)
        {
            val result = BrewResult(
                minutes,
                seconds,
                equipment = equipment,
                coffeeBlend = coffee,
                notes = notes,
                isFavourite = saveAsFavourites,
                date = System.currentTimeMillis(),
                isRecipeDefault = isDefault,
                isResultShared = false,
                recipeKey = recipeKey,
                authorEmail = user.email ?: "",
                author = user.displayName ?: ""
            )
            BrewResultsDAO.saveResult(result)
            Toast.makeText(this, "Result will be saved",
                Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this, "You have been logged out during brewing. Result not saved.",
                Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveAndShareResult() {
        val coffee = blend_name.text.toString()
        val notes = notes_edit_text.text.toString()
        val saveAsFavourites = save_as_favourite.isChecked
        val user = LoginUtil.getCurrentUser()
        if (user != null) {
            val result = BrewResult(
                minutes,
                seconds,
                equipment = EqTypeEnum.AEROPRESS,
                coffeeBlend = coffee,
                notes = notes,
                isFavourite = saveAsFavourites,
                date = System.currentTimeMillis(),
                isRecipeDefault = isDefault,
                isResultShared = true,
                recipeKey = recipeKey,
                authorEmail = user.email ?: "",
                author = user.displayName ?: ""
            )
            BrewResultsDAO.saveResult(result)
            Toast.makeText(this, "Result will be saved",
                Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this, "You have been logged out during brewing. Result not saved.",
                Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (LoginUtil.isUserLoggedIn()) {
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
            return
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
