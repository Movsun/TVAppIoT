package com.example.android.tvappiot

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class SensorListAdapter(val sensorList: ArrayList<Sensor>, val myContext: Context): RecyclerView.Adapter<SensorListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.sensor_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = sensorList.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.btn?.text = sensorList[position].id.toString()
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val btn = itemView?.findViewById<Button>(R.id.sensor_card_btn)
    }
}
