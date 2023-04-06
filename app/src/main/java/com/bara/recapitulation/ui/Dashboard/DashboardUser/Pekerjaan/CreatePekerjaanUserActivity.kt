package com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.DetailPkRequest
import com.bara.recapitulation.databinding.ActivityCreatePekerjaanUserBinding
import com.bara.recapitulation.util.getUserId
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.isNull
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class CreatePekerjaanUserActivity : AppCompatActivity() {
    private val viewModel: CreatePekerjaanUserViewModel by viewModel()
    lateinit var binding: ActivityCreatePekerjaanUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePekerjaanUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    override fun onResume() {
        super.onResume()
        myDropdown()
    }


    private fun myDropdown() {
        val tipe_kerja = resources.getStringArray(R.array.tipe)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.tipe_item_dropdown, tipe_kerja)

        binding.inputAutoCompleteTipe.setAdapter(arrayAdapter)
    }

    private fun mainButton() {
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnCreateTask.setOnClickListener {
            createPekerjaan()
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

        binding.inputTanggal.setOnClickListener {

            DatePickerDialog(this,datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateLable(myCalendar: Calendar?) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.txtTanggal.setText(myCalendar?.time?.let { sdf.format(it) })
    }

    private fun createPekerjaan() {
        if (binding.inputAutoCompleteTipe.text.toString().equals("Tipe")
            || binding.inputJudulTask.isEmpty()
            || binding.inputJamKerja.isEmpty()
            || binding.inputTanggal.isNull()
            || binding.inputDeskripsiTask.isEmpty()) return

        val idPekerjaan = intent.getStringExtra("id_pekerjaan")

        val body = DetailPkRequest(
            id_user = getUserId(),
            id_pekerjaan = idPekerjaan,
            nama_pekerjaan = binding.inputJudulTask.text.toString(),
            desc_pekerjaan = binding.inputDeskripsiTask.text.toString(),
            jam_kerja = binding.inputJamKerja.text.toString(),
            tgl_kerja = binding.txtTanggal.text.toString(),
            tipe = binding.inputAutoCompleteTipe.text.toString(),
        )


        viewModel.createPekerjaanUser(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    viewModel.dialogSuccessCreate(this)
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
}