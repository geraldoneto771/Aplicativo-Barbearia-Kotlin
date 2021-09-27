package com.example.barbeariakotlin.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class CorteModel(): RealmObject() {

    @PrimaryKey
    var id: String = ObjectId().toString()
    var name: String = ""
    var description: String = ""
    var price: Float = 0.0f
    var created: String = ""
    var status: Boolean = false
}