package me.chinobman.whatsapp.models

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class Session (
    @SerializedName("ref")          val ref : String?,
    @SerializedName("wid")          val wid: String?,
    @SerializedName("connected")    val connected : Boolean?,
    @SerializedName("isResponse")   val isResponse : Boolean?,
    @SerializedName("serverToken")  val serverToken : String?,
    @SerializedName("browserToken") val browserToken : String?,
    @SerializedName("clientToken")  val clientToken : String?,
    @SerializedName("secret")       val secret : String?,
    @SerializedName("lc")           val lc : String?,
    @SerializedName("lg")           val lg : String?,
    @SerializedName("locales")      val locales : String?,
    @SerializedName("is24h")        val is24h : String?,
    @SerializedName("protoVersion") val protoVersion : Array<Int>?,
    @SerializedName("binVersion")   val binVersion : String?,
    @SerializedName("battery")      val battery : Int?,
    @SerializedName("plugged")      val plugged : Boolean?,
    @SerializedName("platform")     val platform : String?,
    @SerializedName("features")     val features : Features?,
    @SerializedName("phone")        val phone : Phone?,
    @SerializedName("pushname")     val name : String?,
    @SerializedName("tos")          val tos : Int?
)

data class Phone(
    val device_model : String?
    //TODO "phone":{"wa_version":"2.20.172","mcc":"208","mnc":"020","os_version":"7.1.1","device_manufacturer":"samsung","device_model":"j5xnlte","os_build_number":"NMF26X.J510FNXXU2BRJ4"}
)

data class Features(
    val URL : Boolean?,
    val FLAGS : String?
)