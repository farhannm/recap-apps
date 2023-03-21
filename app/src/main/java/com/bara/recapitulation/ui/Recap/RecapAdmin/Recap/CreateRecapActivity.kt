package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.PkRequest
import com.bara.recapitulation.core.data.source.remote.request.RegisterRequest
import com.bara.recapitulation.databinding.ActivityCreateRecapBinding
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.CreateKaryawanViewModel
import com.bara.recapitulation.util.Pref
import com.inyongtisto.myhelper.extension.isEmpty
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class CreateRecapActivity : AppCompatActivity() {
    private val viewModel: CreatePekerjaanRecapViewModel by viewModel()
    lateinit var binding: ActivityCreateRecapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateRecapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    override fun onResume() {
        super.onResume()
        myDropdown()
    }


    private fun myDropdown() {
        val tipe = resources.getStringArray(R.array.bulan)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.tipe_item_dropdown, tipe)

        binding.inputAutoCompleteBulan.setAdapter(arrayAdapter)
    }

    private fun mainButton() {
        startDate()
        endDate()
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnCreateRecap.setOnClickListener {
            createRecap()
        }
    }

    private fun createRecap() {
        myDropdown()

        if (binding.inputAutoCompleteBulan.text.toString().equals("Bulan")
            || binding.txtStart.text.toString().isEmpty()
            || binding.txtEnd.text.toString().isEmpty()
            || binding.inputTotalJam.isEmpty()
            || binding.inputToleransi.isEmpty()) return

        val userToken = Pref.getUser()?.api_token
        val body = PkRequest(
            binding.inputAutoCompleteBulan.text.toString(),
            binding.txtStart.text.toString(),
            binding.txtEnd.text.toString(),
            binding.inputTotalJam.text.toString(),
            binding.inputToleransi.text.toString()
        )

        print("Current tokens $userToken")

        viewModel.createPekerjaan(userToken, body).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    Pref.getToken(this)
                    viewModel.dialogSuccess(this)
                    val handler = android.os.Handler()
                    handler.postDelayed(object : Runnable {
                        override fun run() {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    }, 3000)
                }
                State.FAILED -> {
                    viewModel.dialogFailed(this)
                }
                State.LOADING -> {
                    viewModel.dialogLoading(this)
                }
            }
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