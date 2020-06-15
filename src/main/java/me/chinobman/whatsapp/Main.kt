package me.chinobman.whatsapp

import com.google.gson.Gson
import me.chinobman.whatsapp.api.WhatsApp
import me.chinobman.whatsapp.api.interfaces.WhatsAppImpl
import me.chinobman.whatsapp.models.Session
import java.util.*
import kotlin.concurrent.timerTask

class Main {


    fun start() {
        //var app : WhatsApp = WhatsAppImpl()
        //app.connect()

        println("1592222084793.--0,{\"status\":200,\"ref\":\"1@uRIpMZl8xnUkuMQJePblZ1hBcBNLUGxO7PkCqmLCRFU8Uj5WNjY5G0LK\",\"ttl\":20000,\"update\":false,\"curr\":\"2.2023.2\",\"time\":1592222084742.0}"
            .split(",")[1].toString())
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Main().start()
        }
    }
}