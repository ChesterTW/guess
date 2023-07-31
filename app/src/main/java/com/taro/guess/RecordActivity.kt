package com.taro.guess

import android.app.Activity
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.taro.guess.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val count = intent.getIntExtra("COUNTER",-1)
        binding.counter.text = count.toString()

        binding.save.setOnClickListener(View.OnClickListener {
            val name = binding.nickname.text.toString()
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER",count)
                .putString("REC_NAME",name)
                .apply()
            finish()
        })

    }
}