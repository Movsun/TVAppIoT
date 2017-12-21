package com.example.android.tvappiot

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_device_list.*

class DeviceListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        // get device list from intent
        @Suppress("UNCHECKED_CAST")
        val devices = intent.getSerializableExtra(Application.SERIALIZE_LIST_NAME) as ArrayList<Device>
        // create recycle view
        deviceListRecycleView.setHasFixedSize(true)
        deviceListRecycleView.layoutManager = GridLayoutManager(this,3)
        deviceListRecycleView.adapter = DeviceListAdapter(devices, this)
    }
}
