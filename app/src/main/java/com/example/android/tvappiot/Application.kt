package com.example.android.tvappiot

object Application {
    val AUTH_BASE_URL = "http://138.48.33.164/"
    val BASE_URL = AUTH_BASE_URL + "api/"

    val CLIENT_ID = 2
    val CLIENT_SECRET = "8PWruyx3tPCL4vzKJVah19xMlJBLXKcQJuu91GpG"
    val GRANT_TYPE = "password"

    val PREF_NAME = "myPref"
    val PREF_TOKEN_NAME = "myToken"

    val DEFAULT_PORT = 2
    val DEFAULT_SCHEDULE = "replace"
    val DEFAULT_CONFIRMED = false
    val SERIALIZE_LIST_NAME = "devicesList"
    val SERIALIZE_DEVICE_NAME = "device"
    val ON_VALUE = "1"
    val OFF_VALUE = "0"
}