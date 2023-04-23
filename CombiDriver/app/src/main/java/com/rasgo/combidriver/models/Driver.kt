package com.rasgo.combidriver.models

import com.beust.klaxon.Klaxon

private val klaxon= Klaxon()
data class Driver(
    val id: String?=null,
    val name: String?=null,
    val email: String?=null,
    val phone: String?=null,
    val rute: String?=null
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object{
        public fun fromJson(json: String)= klaxon.parse<Driver>(json)
    }
}