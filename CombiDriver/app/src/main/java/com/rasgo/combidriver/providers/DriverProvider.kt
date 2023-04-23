package com.rasgo.combidriver.providers

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rasgo.combidriver.models.Driver

class DriverProvider {

    val db = Firebase.firestore.collection("Drivers")

    fun create (driver: Driver): Task<Void>{
        return db.document(driver.id!!).set(driver)
    }
}