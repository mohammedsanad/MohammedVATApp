package com.example.vatapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCalculateVat.setOnClickListener {
            calculateVat()
        }

    }

    private fun calculateVat() {
        val stringVatet = binding.etTotalCost.text.toString()
        val cost = stringVatet.toDouble()

        val selectedId = binding.rgVatOption.checkedRadioButtonId

        val vatPercentage = when(selectedId){
            R.id.rb_vat_10 -> 0.10
            R.id.rb_vat_15 -> 0.15
            else -> 0.20
        }

        var total = vatPercentage * cost

        val roundVat = binding.switchRoundUp.isChecked
        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        val formatedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.txtCostTotal.text = getString(R.string.total_amount, formatedTotal)

    }
}

// End MainActivity