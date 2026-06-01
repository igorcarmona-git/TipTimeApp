package com.example.tiptimeapp

import android.icu.number.NumberFormatter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiptimeapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        binding.tipResult.text = "0.00"
//        binding.calculateButton.setOnClickListener {
//            calculateTip()
//        }
    }

//    private fun calculateTip() {
//        // entries
//        val stringInTextField = binding.costOfService.text.toString()
//        val cost = stringInTextField.toDoubleOrNull() ?: return
//
//        val selectedID = binding.tipOption.checkedRadioButtonId
//        val isRoundUp = binding.roundUpSwitch.isChecked
//
//        // process
//        val tipPercentage = when (selectedID) {
//            R.id.option_twenty_percent -> 0.20
//            R.id.option_eighteen_percent -> 0.18
//            else -> 0.15
//        }
//
//        var tip = cost * tipPercentage
//
//        if(isRoundUp) {
//            tip = kotlin.math.ceil(tip)
//        }
//
//        // output
//        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
//        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
//    }
}