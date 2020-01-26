package com.rubik.brewmoment.model.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object BrewMomentDatabase {

    private var database = FirebaseDatabase.getInstance()

    fun getReference(entity: String): DatabaseReference {
        return database.getReference(entity)
    }
}