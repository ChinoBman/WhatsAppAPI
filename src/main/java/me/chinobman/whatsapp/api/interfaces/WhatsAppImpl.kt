package me.chinobman.whatsapp.api.interfaces

import me.chinobman.whatsapp.api.WhatsApp
import me.chinobman.whatsapp.models.Phone
import me.chinobman.whatsapp.models.Session
import me.chinobman.whatsapp.websocket.WebSocket

class WhatsAppImpl : WhatsApp {

    override fun connect() : Unit = webSocket?.connect()!!

    override fun logout() : Unit = webSocket?.logout()!!

    override var webSocket: WebSocket? = WebSocket();

    override var profile : Session? = webSocket?.session

    override var phone: Phone? = webSocket?.session?.phone
}