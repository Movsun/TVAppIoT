package com.example.android.tvappiot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class CategoryActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

//        settingBtn

        signoutBtn.setOnClickListener {
            // remove access token from share preference
            val sharedPreferences = getSharedPreferences(Application.PREF_NAME, 0)
            val editor = sharedPreferences.edit()
            editor.remove(Application.PREF_TOKEN_NAME)
            editor.apply()
            // start sign in activity
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
            // finish
            finish()
        }

        listDevicesBtn.setOnClickListener {
            val webAppApi = WebAppApi.create(this)
            webAppApi.loadDevices().enqueue(object : Callback<ArrayList<Device>> {
                override fun onResponse(call: Call<ArrayList<Device>>, response: Response<ArrayList<Device>>) {
                    when {
                        response.code() == 200 -> {
                            val intent = Intent(applicationContext, DeviceListActivity::class.java)
                            intent.putExtra(Application.SERIALIZE_LIST_NAME, response.body() as Serializable)
                            startActivity(intent)
                        }
                        response.code() == 401 -> Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
                        else ->  Toast.makeText(applicationContext, response.code().toString()+ ": " + response.message(), Toast.LENGTH_SHORT).show()

                    }
                }
                override fun onFailure(call: Call<ArrayList<Device>>, t: Throwable?) {
                    Log.d("fail", "request fail")
                    t?.printStackTrace()
                }
            })
        }
    }
}
