package com.unitra.task

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK
import androidx.media.MediaBrowserServiceCompat.RESULT_OK

class SecondActivity : AppCompatActivity() {

    public val itemUnlock:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnConfirm:Button = findViewById(R.id.btn_confirm)

        btnConfirm.setOnClickListener{
            sendData()
        }
    }

    private fun sendData() {
        val data = Intent()
        data.putExtra("unlock",true)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onBackPressed() {
        sendData()
        super.onBackPressed()
    }


}