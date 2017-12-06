package com.example.android.tvappiot

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class DeviceListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        // get device list from intent
        val intent = intent
        @Suppress("UNCHECKED_CAST")
        val devices = intent.getSerializableExtra(Application.SERIALIZE_LIST_NAME) as ArrayList<Device>
        // create recycle view
        val recyclerView = findViewById<RecyclerView>(R.id.deviceListRecycleView)
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this@DeviceListActivity, 3)
        recyclerView.layoutManager = layoutManager
        val mAdapter = DeviceListAdapter(devices, this)
        recyclerView.adapter = mAdapter
    }
}
