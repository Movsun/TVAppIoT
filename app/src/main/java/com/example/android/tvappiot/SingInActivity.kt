package com.example.android.tvappiot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_sing_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingInActivity : Activity(), Callback<Token> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_in)

        signInBtn.setOnClickListener {
            val emailText = emailText.text.toString()
            val passwordText = passwordText.text.toString()
            val authApi = AuthApi.create()
            authApi.authenticate(PasswordGrantBody(username = emailText, password = passwordText)).enqueue(this@SingInActivity)
        }
    }

    override fun onResponse(call: Call<Token>?, response: Response<Token>?) {
        if (response?.code() == 200){
            // store token as share pref
            val sharePref = getSharedPreferences(Application.PREF_NAME, 0)
            val editor = sharePref.edit()
            editor.putString(Application.PREF_TOKEN_NAME, response.body()?.access_token)
            editor.apply()
            // start category
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // todo display message popup
            Log.d("code, message", response?.code().toString() + response?.message().toString())
        }
    }

    override fun onFailure(call: Call<Token>?, t: Throwable?) {
        t?.printStackTrace()
    }
}
