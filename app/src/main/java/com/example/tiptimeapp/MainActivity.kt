package com.example.tiptimeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiptimeapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Checa se o estado salvo é nulo ou não, se for nulo, exibe o texto "Tip Amount:",
        // caso contrário, exibe o valor salvo no estado
        if (savedInstanceState == null) {
            binding.tipResult.text = "Tip Amount:"
        }else{
            binding.tipResult.text = savedInstanceState.getString("tipResult")
        }

        binding.btCalculate.setOnClickListener {
            calculateTip()
        }

    }

    private fun calculateTip() {
        // entries
        val stringInTextField = binding.etCostOfServiceInput.text.toString()

        // Checa se é valor nulo ou não, se for nulo "?:", retorna a função sem fazer nada
        val cost = stringInTextField.toDoubleOrNull() ?: return

        val selectedID = binding.tipOptions.checkedRadioButtonId
        val isRoundUp = binding.switchRoundUp.isChecked

        // process
        val tipPercentage = when (selectedID) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercentage

        if(isRoundUp) {
            tip = kotlin.math.ceil(tip)
        }

        // ‘output’
        // Formata o valor da ‘tip’ para o formato de moeda local, usando a classe NumberFormat
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    // Salva o estado da aplicação, nesse caso, se a tela rotacionar ele salva o valor
    // do resultado no estado
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("tipResult", binding.tipResult.text.toString())
    }
}