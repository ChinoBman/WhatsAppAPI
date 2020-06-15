package me.chinobman.whatsapp.api.presence

import me.chinobman.whatsapp.websocket.WebSocket
import org.java_websocket.client.WebSocketClient

class Presence(private var socket : WebSocket) {



    fun setPresence(activity: Activity) {

    }

    fun getPresence() {

    }

    private fun update() {
        if(socket.loggedIn) {
            this.socket.send("")
        }
    }

}

data class Activity(
    var status : String
)