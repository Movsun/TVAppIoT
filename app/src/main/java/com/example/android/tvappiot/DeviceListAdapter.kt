package com.example.android.tvappiot

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class DeviceListAdapter internal constructor(private var deviceList: ArrayList<Device>, private val myContext: Context) : RecyclerView.Adapter<DeviceListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.device_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = deviceList[position]

        // display device name, mac, etc.
        holder.btn.text = device.getDetailText()
        // set on device click action listener
        holder.btn.setOnClickListener {
            val device1 = deviceList[position]
            Log.d("dev log", device1.toString())
            // display device detail activity
            val intent = Intent(this@DeviceListAdapter.myContext, DeviceDetailActivity::class.java)
            intent.putExtra(Application.SERIALIZE_DEVICE_NAME, device1)
            this@DeviceListAdapter.myContext.startActivity(intent)
            // load and display sensor data if available
        }
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn: Button = itemView.findViewById(R.id.device_card_btn)
    }


}
