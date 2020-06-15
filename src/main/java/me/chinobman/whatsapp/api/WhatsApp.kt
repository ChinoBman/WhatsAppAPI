package me.chinobman.whatsapp.api

import me.chinobman.whatsapp.models.Phone
import me.chinobman.whatsapp.models.Session
import me.chinobman.whatsapp.websocket.WebSocket

interface WhatsApp {

    fun connect()

    fun logout()

    var profile : Session?

    var phone : Phone?

    var webSocket : WebSocket?

}