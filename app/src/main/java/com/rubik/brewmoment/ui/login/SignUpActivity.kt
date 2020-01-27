package com.rubik.brewmoment.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnSuccessListener
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
            if (validateFields())
            {
                val promise = FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email_edit_text.text.toString())
                promise.addOnSuccessListener {
                    if (it.signInMethods!!.isEmpty())
                        createUser(
                            email_edit_text.text.toString(),
                            password_edit_text.text.toString(),
                            username_edit_text.text.toString()
                        )
                    else {
                        email_edit_text.error = "Email is occupied"
                    }
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        var result = true
        if (username_edit_text.text.isBlank())
        {
            username_edit_text.error = "Username cannot be blank"
            result = false
        }
        if (email_edit_text.text.isBlank())
        {
            email_edit_text.error = "Email cannot be blank"
            result = false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email_edit_text.text).matches())
        {
            email_edit_text.error = "Email is not valid"
            result = false
        }
        if (password_edit_text.text.length < 8)
        {
            password_edit_text.error = "Password has to be at least 8 chars long"
            result = false
        }
        return result
    }

    private fun createUser(email: String, password: String, username: String) {
        val mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
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
