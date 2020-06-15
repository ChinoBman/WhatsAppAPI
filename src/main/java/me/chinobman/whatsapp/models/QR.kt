package me.chinobman.whatsapp.models

import com.google.gson.annotations.SerializedName

data class QR(
    @SerializedName("status") val status : Int?,
    @SerializedName("ref") val reference : String?,
    @SerializedName("ttl") val ttl : Long?,
    @SerializedName("update") val update : Boolean?,
    @SerializedName("curr") val currentVersion : String?,
    @SerializedName("time") val time : Long?
)