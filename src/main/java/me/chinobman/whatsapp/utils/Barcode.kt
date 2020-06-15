package me.chinobman.whatsapp.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.QRCode
import java.awt.image.BufferedImage
import java.io.File
import java.lang.NullPointerException

class Barcode {

    private var filePath = File("./img.png")
    private val encodeMap = mapOf(EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.L)
    private val type : BarcodeFormat = BarcodeFormat.QR_CODE

    private val HEIGHT = 200
    private val WIDTH = 200

    @Deprecated("Needs to be removed in the future.")
    private fun createQR(arg : String, boolean : Boolean) {
        var format = QRCodeWriter().encode(arg, type, WIDTH, HEIGHT)
        MatrixToImageWriter.writeToFile(format, "PNG", filePath)
    }

    private fun createQR(arg : String, file : File) : File {
        var format = QRCodeWriter().encode(arg, type, WIDTH, HEIGHT, encodeMap)
        MatrixToImageWriter.writeToFile(format, "PNG", file)
        return file
    }

    private fun createQR(arg : String) : BufferedImage {
        var format = QRCodeWriter().encode(arg, type, WIDTH, HEIGHT)
        return MatrixToImageWriter.toBufferedImage(format)
    }

    companion object {
        fun generate(arg: String, file: File) : File {
            var nF = Barcode().createQR(arg, file)
            if(nF.exists()) return nF else throw NullPointerException("Couldn't generate QR Code.")
        }
    }


}