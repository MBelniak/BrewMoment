package com.rubik.brewmoment.ui.results

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.rubik.brewmoment.R
import com.rubik.brewmoment.model.data.BrewResult
import com.rubik.brewmoment.model.data.BrewResultsDAO
import kotlinx.android.synthetic.main.activity_result_details.*
import java.sql.Date

class ResultDetailsActivity : AppCompatActivity() {

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
        val key = intent.extras?.getString("ResultKey") ?: ""
        val isMine = intent.extras?.getBoolean("IsMyResult") ?: true
        brewResult = if (key != "")
            BrewResultsDAO.getById(key, isMine)
        else
            BrewResult(0, 0, "", "",
                false, Date(SystemClock.elapsedRealtime()), true, isResultShared = false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
