package com.bara.recapitulation.ui.Recap.RecapUser.Recap.DetailTaskUser

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.DetailPkRequest
import com.bara.recapitulation.databinding.ActivityDetailTaskUserBinding
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailTaskUserActivity : AppCompatActivity() {

    private val viewModel: DetailTaskUserViewModel by viewModel()
    lateinit var binding: ActivityDetailTaskUserBinding
    private val detailPk by extra<DetailPekerjaan>()
    private val adapterDetailPekerjaan = DetailPekerjaanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTaskUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
        setData()
        myDropdown()
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun mainButton() {
        binding.recapDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnDeleteTask.setOnClickListener {
            deleteTask()
        }

        binding.btnSaveTask.setOnClickListener {
            updateTask()
        }

        myCalendar()
    }

    private fun setData() {
        viewModel.getDetailPk(detailPk?.id).observe(this){
            println("ID BOSSS ${detailPk?.id}")
            binding.apply {
                inputAutoCompleteTipe.setText("${detailPk?.tipe}")
                inputJudulTask.setText("${detailPk?.nama_pekerjaan}")
                txtTanggal.setText("${detailPk?.tgl_kerja}")
                inputJamKerja.setText("${detailPk?.jam_kerja}")
                inputDeskripsiTask.setText("${detailPk?.desc_pekerjaan}")
            }
        }
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

    private fun myDropdown() {
        val tipe_kerja = resources.getStringArray(R.array.tipe)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.tipe_item_dropdown, tipe_kerja)

        binding.inputAutoCompleteTipe.setAdapter(arrayAdapter)
    }


    private fun deleteTask() {
        val idDetailPk = detailPk?.id
        viewModel.deleteDetailPk(idDetailPk).observe(this){
            when (it.state) {
                State.SUCCESS -> {
                    showToast("Berhasil menghapus!")
                }
                State.FAILED -> {
                    viewModel.dialogSuccessDelete(this)
                    val handler = android.os.Handler()
                    handler.postDelayed(object : Runnable {
                        override fun run() {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    }, 3000)
                    showToast("Berhasil menghapus!")
                }
                State.LOADING -> {}
            }
        }
    }

    private fun updateTask() {
        if (binding.inputAutoCompleteTipe.text.isEmpty()
            || binding.inputJudulTask.isEmpty()
            || binding.inputJamKerja.isEmpty()
            || binding.inputTanggal.isNull()
            || binding.inputDeskripsiTask.isEmpty()) return

        var idDetailPk = detailPk?.id

        val body = DetailPkRequest(
            id = idDetailPk,
            nama_pekerjaan = binding.inputJudulTask.text.toString(),
            desc_pekerjaan = binding.inputDeskripsiTask.text.toString(),
            jam_kerja = binding.inputJamKerja.text.toString(),
            tgl_kerja = binding.txtTanggal.text.toString(),
            tipe = binding.inputAutoCompleteTipe.text.toString(),
        )


        viewModel.updateDetailPk(idDetailPk, body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    viewModel.dialogSuccessEdit(this)
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