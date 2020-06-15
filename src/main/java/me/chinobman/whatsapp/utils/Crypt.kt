package me.chinobman.whatsapp.utils

import org.whispersystems.curve25519.Curve25519
import org.whispersystems.curve25519.Curve25519KeyPair

class Crypt {

    fun createCurve25519() : Curve25519KeyPair {
        var key = Curve25519.getInstance(Curve25519.BEST)
        return key.generateKeyPair()
    }

    fun createKey(bytes : ByteArray) {

    }
}