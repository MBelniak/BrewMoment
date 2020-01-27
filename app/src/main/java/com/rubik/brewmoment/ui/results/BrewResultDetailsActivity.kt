package com.rubik.brewmoment.ui.results

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.model.data.BrewResultsDAO
import kotlinx.android.synthetic.main.activity_result_details.*
import java.sql.Date

class BrewResultDetailsActivity : AppCompatActivity() {

    private var actionBar: ActionBar? = null
    private lateinit var brewResult: BrewResult
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_details)
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initBrewResult(intent)

        result_coffee_used.text = resources.getString(R.string.result_coffee_used, brewResult.coffeeBlend)
        result_total_brew_time.text = resources.getString(R.string.result_total_brew_time,
            brewResult.brewTimeMinutes.toString(), brewResult.brewTimeSeconds.toString())
        result_notes.text = resources.getString(R.string.result_notes, brewResult.notes)
    }

    private fun initBrewResult(intent: Intent) {
        val key = intent.extras?.getString("ResultKey")
        if (key != null) {
            val rec = BrewResultsDAO.getByKey(key)
            if (rec == null) {
                brewResult = BrewResultsDAO.getDefaultResult()
                Toast.makeText(this, "Recipe has been deleted in the meantime.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                brewResult = rec
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
