package com.example.android.tvappiot

import android.util.Base64
import java.io.Serializable
import java.nio.charset.StandardCharsets

data class Device (
        val id: Int,
        private val device_name: String,
        private val device_description: String,
        private val device_eui: String,
        private val device_mac: String,
        private val device_type: String,
        val device_version: String,
        val share_status: String
) : Serializable{
    internal fun getDetailText(): String {
        val mac: String = when (this.device_description) {
            "LoRa" -> this.device_eui
            "BLE", "WiFi" -> this.device_mac
            else -> ""
        }
        return ("Name: " + this.device_name + "\n"
                + "Device: " + this.device_description + "\n"
                + "MAC: " + mac + "\n"
                + "Type: " + this.device_type)
    }

    fun generateDownlink(payload: String): Downlink? {
        val b64Payload = Base64.encodeToString(payload.toByteArray(), Base64.DEFAULT)
        return when (this.device_description) {
            "LoRa", "BLE", "WiFi" -> Downlink(b64Payload)
            else -> null
        }
    }
}

data class Sensor(val id: Int)

data class Token (
        val token_type: String,
        val access_token: String,
        val expires_in: Int,
        val refresh_token: String
)

data class PasswordGrantBody (
        val username: String,
        val password: String,
        val client_id: Int = Application.CLIENT_ID,
        val client_secret: String = Application.CLIENT_SECRET,
        val grant_type: String = Application.GRANT_TYPE
)

class Downlink (
        val payload: String,
        val port: Int = Application.DEFAULT_PORT,
        val confirmed: Boolean = Application.DEFAULT_CONFIRMED,
        val schedule: String = Application.DEFAULT_SCHEDULE
)

data class Uplink (
        private val payload: String
){
    fun getDecodePayload(): String {
        val data = Base64.decode(payload, Base64.DEFAULT)
        return String(data, StandardCharsets.UTF_8)
    }
}
