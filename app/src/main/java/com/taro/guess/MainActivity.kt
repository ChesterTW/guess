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
    val secretNumber = SecretNumber();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater);
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        Log.d("MainActivity", "secret: " + secretNumber.secret)


    }

    fun check(view: View){

        val n:Int = binding.number.text.toString().toInt()
        Log.d("MainActivity", "check: $n")

        val diff = secretNumber.validate(n)
        var message = "Yes, Bingo."
        if ( diff < 0 ){
            message = "Bigger"
        }else if ( diff > 0 ){
            message = "Lower"
        }

//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i -> binding.number.setText("") })
            .show()


    }
}