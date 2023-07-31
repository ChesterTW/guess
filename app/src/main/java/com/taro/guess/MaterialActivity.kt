package com.taro.guess

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.taro.guess.databinding.ActivityMaterialBinding

class MaterialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMaterialBinding
    private val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        this.binding = ActivityMaterialBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        Log.d(TAG, "secret: " + secretNumber.secret)
        Log.d(TAG, "onCreate: ")

/*        activityBinding.fab.setOnClickListener(
            View.OnClickListener {
                    view -> Snackbar.make(view, "Replace with your own icon.", Snackbar.LENGTH_LONG).show()
            })*/

        binding.includedMaterial.btok.setOnClickListener {
            check()
        }

        binding.fab.setOnClickListener(
            View.OnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.replay_game))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    secretNumber.reset()
                    binding.includedMaterial.counter.text = secretNumber.count.toString()
                    binding.includedMaterial.number.setText("")
                }
                .setNeutralButton(getString(R.string.cancel),null)
                .show()
            }
        )

        binding.includedMaterial.counter.text = secretNumber.count.toString()
        Log.d(TAG, "onCreate: " + secretNumber.secret)

        val count = getSharedPreferences("guess", MODE_PRIVATE).getInt("REC_COUNTER",-1)
        val name = getSharedPreferences("guess", MODE_PRIVATE).getString("REC_NAME",null)

        Log.d(TAG, "data: $name\t $count")

    }

    private fun check() {
        val n: Int = binding.includedMaterial.number.text.toString().toInt()
        Log.d(TAG, "check: $n")

        val diff = secretNumber.validate(n)
        var message = getString(R.string.yes_bingo)
        if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff > 0) {
            message = getString(R.string.lower)
        } else if(secretNumber.count < 3 && diff == 0){
            message = getString(R.string.excellent_the_number_is_6)
        }

        binding.includedMaterial.counter.text = secretNumber.count.toString()
        //        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(
                getString(R.string.ok),
                DialogInterface.OnClickListener { _, _ ->
                    binding.includedMaterial.number.setText("")
                    if(diff==0){
                        val intent = Intent(this, RecordActivity::class.java)
                        intent.putExtra("COUNTER",secretNumber.count)
                        startActivity(intent)
                    }
                })
            .show()
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}