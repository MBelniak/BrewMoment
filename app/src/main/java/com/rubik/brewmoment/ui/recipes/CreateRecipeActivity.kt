package com.rubik.brewmoment.ui.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ScrollView
import android.widget.ViewFlipper
import androidx.appcompat.app.ActionBar
import com.rubik.brewmoment.R
import kotlinx.android.synthetic.main.activity_brew.view.*
import kotlinx.android.synthetic.main.activity_create_recipe.*

class CreateRecipeActivity : AppCompatActivity() {

    private var actionBar: ActionBar? = null
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var form1NextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        viewFlipper = findViewById(R.id.viewFlipper)
        form1NextButton = (findViewById<ScrollView>(R.id.form1)).findViewById(R.id.next_button)
        form1NextButton.setOnClickListener{
            viewFlipper.showNext()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
