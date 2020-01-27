package com.rubik.brewmoment.model.data

import android.content.ContentValues.TAG
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.rubik.brewmoment.model.EqTypeEnum

object BrewResultsDAO {
    private val databaseRef = BrewMomentDatabase.getReference("results")
    val allResults: MutableLiveData<List<BrewResult>> = MutableLiveData()

    private val connectedRef = BrewMomentDatabase.getReference(".info/connected")

    init {
        connectedRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val connected = snapshot.getValue(Boolean::class.java) ?: false
                if (connected) {
                    Log.d(TAG, "connected")
                } else {
                    Log.d(TAG, "not connected")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Listener was cancelled")
            }
        })

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    allResults.postValue(dataSnapshot.children.map
                        { it.getValue(BrewResult::class.java) } as List<BrewResult>?)
                }
            }
        })
    }

    fun saveResult(result: BrewResult) {
        InsertBrewResultAsyncTask(databaseRef).execute(result)
    }


    fun update(result: BrewResult) {
        val updateResultAsyncTask = UpdateBrewResultAsyncTask(databaseRef)
            .execute(result)
    }
    fun delete(result: BrewResult) {
        val deleteResultAsyncTask = DeleteBrewResultAsyncTask(databaseRef)
            .execute(result)
    }
    private class InsertBrewResultAsyncTask(val databaseRef: DatabaseReference) :
        AsyncTask<BrewResult, Unit, Unit>() {

        override fun doInBackground(vararg result: BrewResult) {
            val key: String? = databaseRef.push().key
            if (key != null) {
                result[0].key = key
                val res = databaseRef.child(key).setValue(result[0])
                if (res.isCanceled) {
                    println("aaaaaaaa")
                }
                if (res.isSuccessful)
                {
                    println("aaaaaaaa")
                }
                if (res.isComplete)
                {
                    println("aaaaaaaa")
                }
            }
        }
    }
    private class UpdateBrewResultAsyncTask(val databaseRef: DatabaseReference) :
        AsyncTask<BrewResult, Unit, Unit>() {

        override fun doInBackground(vararg result: BrewResult) {
            val key = result[0].key
            databaseRef.child(key).setValue(result[0])
        }
    }
    private class DeleteBrewResultAsyncTask(val databaseRef: DatabaseReference) :
        AsyncTask<BrewResult, Unit, Unit>() {


        override fun doInBackground(vararg result: BrewResult) {
            val key = result[0].key
            databaseRef.child(key).removeValue()
        }

    }

//    fun getByKey(key: String): BrewResult {
//        //TODO
//    }

    fun getByKey(key: String): BrewResult? {
        if (allResults.value != null)
            for (result in allResults.value!!)
                if (result.key == key)
                    return result
        return null
    }

    fun getDefaultResult(): BrewResult {
        return BrewResult(
            0,
            0,
            equipment = EqTypeEnum.AEROPRESS,
            coffeeBlend = "Ethiopia Aricha",
            notes = "",
            isFavourite = false,
            date = System.currentTimeMillis(),
            isRecipeDefault = true,
            isResultShared = false,
            authorEmail = "",
            author = ""
        )
    }

    fun getAllResults(): LiveData<List<BrewResult>> {
        return allResults
    }

}