package com.example.android.tvappiot

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_sensor_list.*

class SensorListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_list)

        sensorListRecycleView.setHasFixedSize(true)
        sensorListRecycleView.layoutManager = GridLayoutManager(this, 3)
        sensorListRecycleView.adapter = SensorListAdapter(arrayListOf(Sensor(1), Sensor(2)), this)
    }
}
