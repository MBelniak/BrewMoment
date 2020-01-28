package com.rubik.brewmoment.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.rubik.brewmoment.MainActivity
import com.rubik.brewmoment.R
import kotlinx.android.synthetic.main.fragment_login.*


class LogInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        sign_in_button.setOnClickListener{
            val email: String = email_edit_text.text.toString()
            val password: String = password_edit_text.text.toString()
            val mAuth = FirebaseAuth.getInstance()
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(activity!!.applicationContext, "You have been signed in.",
                            Toast.LENGTH_LONG).show()
                        startActivity(Intent(activity, MainActivity::class.java))
                        activity?.finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(activity!!.applicationContext, "Authentication failed.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        sign_up_text_view.movementMethod = LinkMovementMethod.getInstance()
        sign_up_text_view.isClickable = true
        sign_up_text_view.setOnClickListener {
            startActivity(Intent(activity, SignUpActivity::class.java))
        }
    }
}