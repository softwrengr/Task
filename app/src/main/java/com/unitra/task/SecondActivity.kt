package com.unitra.task

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK
import androidx.databinding.DataBindingUtil
import androidx.media.MediaBrowserServiceCompat.RESULT_OK
import com.unitra.task.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val binding: ActivitySecondBinding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        binding.btnConfirm.setOnClickListener{
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
        //sendData()
        super.onBackPressed()
    }


}