package com.rubik.brewmoment.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.rubik.brewmoment.MainActivity
import com.rubik.brewmoment.R
import kotlinx.android.synthetic.main.activity_sing_up.*


class SignUpActivity : AppCompatActivity() {

    private var actionBar: ActionBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        sign_up_button.setOnClickListener{
            //TODO check if filled
            createUser(email_edit_text.text.toString(), password_edit_text.text.toString(), username_edit_text.text.toString())
        }
    }

    private fun createUser(email: String, password: String, username: String) {
        val mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(username).build()
                    user!!.updateProfile(profileUpdates)
                    Toast.makeText(this, "You have been registered and signed in.",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

}
