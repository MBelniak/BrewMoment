package com.rubik.brewmoment.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.rubik.brewmoment.MainActivity
import com.rubik.brewmoment.R
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_account, container, false)
        val mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        rootView.findViewById<TextView>(R.id.text_logged_in_as).text =
            resources.getString(R.string.logged_in_as, user?.email ?: "anonymous")
        return rootView
    }

    override fun onStart() {
        super.onStart()
        log_out_button.setOnClickListener{
            val mAuth = FirebaseAuth.getInstance()
            mAuth.signOut()
            Toast.makeText(activity, "You have been logged out.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        }
    }
}
