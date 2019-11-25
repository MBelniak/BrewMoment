package com.rubik.brewmoment

import android.app.Application
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth


//class ApplicationStart : Application() {
//    override fun onCreate() {
//        super.onCreate()
//        var firebaseAuth = FirebaseAuth.getInstance()
//        val currentUser = firebaseAuth.currentUser
//        if (currentUser == null) {
//            // Take user to log in screen
//            val intent = Intent(this, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//        } else {
//            // User already logged in
//            val intent = Intent(this, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//        }
//    }
//}