package com.example.android.tvappiot

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences(Application.PREF_NAME, 0)
        val token  = sharedPref.getString(Application.PREF_TOKEN_NAME, "")
        val intent: Intent
        intent = if (token == ""){
            Intent(this, SingInActivity::class.java)
        } else {
            Intent(this, CategoryActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
