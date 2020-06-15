package me.chinobman.whatsapp.websocket

import com.google.gson.Gson
import com.google.gson.JsonParseException
import me.chinobman.whatsapp.models.QR
import me.chinobman.whatsapp.models.Session
import me.chinobman.whatsapp.utils.Barcode
import me.chinobman.whatsapp.utils.Crypt
import me.chinobman.whatsapp.utils.StringUtil
import me.chinobman.whatsapp.websocket.enumerations.State
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.io.File
import java.lang.NullPointerException
import java.net.URI
import java.util.*
import kotlin.concurrent.timerTask

class WebSocket() : WebSocketClient(URI("wss://web.whatsapp.com/ws"), mapOf("Origin" to "https://web.whatsapp.com")) {

    private var gson = Gson()

    private val HOST : String? = "wss://web.whatsapp.com/ws"
    private val VERSION : String? = "2,2023,2"
    private val BROWSER : Array<String> = arrayOf("\"Windows\"", "\"Edge\"", "\"10\"")

    private var bArray = ByteArray(25)
    private var randomize : Unit = Random().nextBytes(bArray) /* Already defined as Unit */
    private val clientId : String? = Base64.getEncoder().encodeToString(bArray)

    private var util = Crypt()
    private var puk = util.createCurve25519().publicKey

    private var qrCode : QR? = null
    var session : Session? = null
    var loggedIn : Boolean = false

    override fun onOpen(p0: ServerHandshake?) {
        var initialize = "${System.currentTimeMillis()}.--0,[\"admin\", \"${State.INIT.arg}\", [${VERSION}], ${BROWSER.asList()}, \"${clientId}\", true]"
        this.send(initialize)
        this.connectionLostTimeout = 0
        Timer().scheduleAtFixedRate(timerTask { keepAlive() }, 25000L, 25000L)
    }

    override fun onMessage(arg: String?) {
        println(arg)
        if(arg?.contains("ref")!! && !loggedIn && arg?.contains(".--0")) {
            isLoggedIn(arg, this).run {
                val barcode: Unit = getBarcode(this)/* After barcode generator nothing will continue this is pure to authenticate.*/
            }
        }


        if(arg.startsWith("s1")) {
            try {
                this.session = StringUtil.toJson(arg, Session::class.java)
                loggedIn = true

                var deSe = Base64.getDecoder().decode(session?.secret)
                println(deSe.asList())
            } catch (ex : JsonParseException) {
                System.`err`.println("Couldn't parse session request. Bad JSON.")
            }
        }
    }

    override fun onClose(i: Int, s: String?, p2: Boolean) { logout(); }

    override fun onError(p0: java.lang.Exception?) { System.`err`.println(p0?.localizedMessage!!).run { logout(); } }

    fun logout() {
        if(loggedIn) this.send("goodbye,[\"admin\",\"Conn\",\"disconnect\"]")
        println("Closing Connection.")

        Thread.sleep(1000).run {
            connection.close()
        }
    }

    private fun isLoggedIn(arg : String, client : WebSocketClient) : String {
        this.qrCode = gson.fromJson(arg.split("--0,")[1], QR::class.java)
        var qr = "${qrCode?.reference},${Base64.getEncoder().encodeToString(puk)},${clientId}"
        if(qr.length > 1) return qr else throw NullPointerException("Failed to login.")
    }

    private fun getBarcode(arg : String) {
        var tempFile = File("./code.png")
        Barcode.generate(arg, tempFile).canRead().let { println("File created.") /* Open the file automatically actually. */ }
    }

    private fun keepAlive() {
        this.send("?,,")
    }
}