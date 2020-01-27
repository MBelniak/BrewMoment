package com.rubik.brewmoment.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo

object LoginUtil {
    fun isUserLoggedIn() : Boolean
    {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return currentUser != null
    }

    fun getCurrentUserEmail(): String?
    {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return currentUser?.email
    }

    fun getCurrentUser(): FirebaseUser?
    {
        return FirebaseAuth.getInstance().currentUser
    }

    fun getCurrentUserUsername(): String? {
        return FirebaseAuth.getInstance().currentUser?.displayName
    }
}