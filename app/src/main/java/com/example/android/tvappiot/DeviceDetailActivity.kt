package com.example.android.tvappiot

import android.app.Activity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_device_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeviceDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_detail)

        // get device
        val intent = intent
        val device = intent.getSerializableExtra(Application.SERIALIZE_DEVICE_NAME) as Device

        // set up display text
        deviceDetailText.text = device.getDetailText()

        val webAppApi = WebAppApi.create(this)
        webAppApi.getUplinkMessage(device.id).enqueue(object : Callback<Uplink> {
            override fun onResponse(call: Call<Uplink>, response: Response<Uplink>) {
                when {
                    response.code() == 200 -> {
                        // update value text view
                        val str = "Value: " + response.body()!!.getDecodePayload()
                        sensorValueText.text = str
                    }
                    response.code() == 204 -> {
                        // update value text view
                        val str = "Value: " + "Not Content"
                        sensorValueText.text = str
                    }
                    else -> Log.d("code, message ", response.code().toString() + response.message())
                }
            }

            override fun onFailure(call: Call<Uplink>, t: Throwable) {
                t.printStackTrace()
            }
        })

        deviceOnBtn.setOnClickListener {
            webAppApi.sendDownlinkMessage(device.id, device.generateDownlink(Application.ON_VALUE)).enqueue(downlinkCallback)
        }
        deviceOffBtn.setOnClickListener {
            webAppApi.sendDownlinkMessage(device.id, device.generateDownlink(Application.OFF_VALUE)).enqueue(downlinkCallback)
        }
    }

    private var downlinkCallback: Callback<String> = object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            Log.d("code, message", response.code().toString() + response.message())
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            t.printStackTrace()
        }
    }
}
