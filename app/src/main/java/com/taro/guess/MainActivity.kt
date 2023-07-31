package com.taro.guess

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.taro.guess.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val secretNumber = SecretNumber()
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        Log.d(TAG, "secret: " + secretNumber.secret)

        val count = getSharedPreferences("guess", MODE_PRIVATE).getInt("REC_COUNTER",-1)
        val name = getSharedPreferences("guess", MODE_PRIVATE).getString("REC_NAME",null)

        Log.d(TAG, "data: $name\t $count")



    }

    fun check(view: View){

        val n:Int = binding.number.text.toString().toInt()
        Log.d(TAG, "check: $n")

        val diff = secretNumber.validate(n)
        var message = getString(R.string.yes_bingo)
        if ( diff < 0 ){
            message = getString(R.string.bigger)
        }else if ( diff > 0 ){
            message = getString(R.string.lower)
        }

//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), DialogInterface.OnClickListener { _, _ -> binding.number.setText("") })
            .show()


    }
}