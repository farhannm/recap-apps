package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bara.recapitulation.R
import com.bara.recapitulation.databinding.ActivityCreateRecapBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateRecapActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateRecapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateRecapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        startDate()
        endDate()
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun startDate() {
        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayofMonth)
            updateLableStart(myCalendar)
        }

        binding.btnDatePickerStart.setOnClickListener {

            DatePickerDialog(this, R.style.DialogTheme,datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun endDate(){
        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayofMonth)
            updateLableEnd(myCalendar)
        }

        binding.btnDatePickerEnd.setOnClickListener {

            DatePickerDialog(this, R.style.DialogTheme,datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateLableStart(myCalendar: Calendar?) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.txtStart.text = myCalendar?.time?.let { sdf.format(it) }
    }

    private fun updateLableEnd(myCalendar: Calendar?) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.txtEnd.text = myCalendar?.time?.let { sdf.format(it) }
    }
}