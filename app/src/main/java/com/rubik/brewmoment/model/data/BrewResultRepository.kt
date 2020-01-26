package com.rubik.brewmoment.model.data

import android.os.AsyncTask
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class BrewResultRepository {

    val databaseRef = BrewMomentDatabase.getReference("brew_results")
    val allResults: MutableList<BrewResult> = mutableListOf()

    init {
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    allResults.clear()
                    for (rec in p0.children) {
                        val result = rec.getValue(BrewResult::class.java)
                        allResults.add(result!!)
                    }
                }
            }

        })
    }

    fun insert(result: BrewResult) {
        val insertResultAsyncTask = InsertResultAsyncTask(databaseRef).execute(result)
    }

    fun update(result: BrewResult) {
        val updateResultAsyncTask = UpdateResultAsyncTask(databaseRef).execute(result)
    }


    fun delete(result: BrewResult) {
        val deleteResultAsyncTask = DeleteResultAsyncTask(databaseRef).execute(result)
    }


    companion object {
        private class InsertResultAsyncTask(val databaseRef: DatabaseReference) : AsyncTask<BrewResult, Unit, Unit>() {
            override fun doInBackground(vararg result: BrewResult) {
                val key: String? = databaseRef.push().key
                if (key != null) {
                    result[0].key = key
                }
                key?.let { databaseRef.child(it).setValue(result)}
            }
        }

        private class UpdateResultAsyncTask(val databaseRef: DatabaseReference) : AsyncTask<BrewResult, Unit, Unit>() {
            override fun doInBackground(vararg result: BrewResult) {
                val key = result[0].key
                databaseRef.child(key).setValue(result)
            }
        }

        private class DeleteResultAsyncTask(val databaseRef: DatabaseReference) : AsyncTask<BrewResult, Unit, Unit>() {
            override fun doInBackground(vararg result: BrewResult) {
                val key = result[0].key
                databaseRef.child(key).removeValue()
            }
        }
    }
}
