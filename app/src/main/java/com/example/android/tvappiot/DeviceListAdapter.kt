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
        val v = LayoutInflater.from(parent.context).inflate(R.layout.device_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // display device name, mac, etc. and add listener
        holder.btn.text = deviceList[position].getDetailText()
        holder.btn.setOnClickListener {
            // display device detail activity
            val intent = Intent(this.myContext, DeviceDetailActivity::class.java)
            intent.putExtra(Application.SERIALIZE_DEVICE_NAME, deviceList[position])
            this.myContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn: Button = itemView.findViewById(R.id.device_card_btn)
    }


}
