package com.bara.recapitulation.ui.Dashboard.DashboardUser.Task

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bara.recapitulation.R
import com.bara.recapitulation.databinding.ActivityAuthBinding
import com.bara.recapitulation.databinding.ActivityCreateTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intentView()
    }

    override fun onResume() {
        super.onResume()
        myDropdown()
    }


    private fun myDropdown() {
        val tipe = resources.getStringArray(R.array.tipe_kerja)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.tipe_item_dropdown, tipe)

        binding.txtAutoCompleteType.setAdapter(arrayAdapter)
    }

    private fun intentView() {
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        myCalendar()
    }

    private fun myCalendar() {
        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayofMonth)
            updateLable(myCalendar)
        }

        binding.btnDatePicker.setOnClickListener {

            DatePickerDialog(this, R.style.DialogTheme,datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateLable(myCalendar: Calendar?) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.txtTanggal.text = myCalendar?.time?.let { sdf.format(it) }
    }
}