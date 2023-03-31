package com.bara.recapitulation.ui.Dashboard.DashboardUser.Pekerjaan

import android.app.Activity
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.DetailPkRequest
import com.bara.recapitulation.databinding.ActivityCreatePekerjaanUserBinding
import com.bara.recapitulation.util.getIdPekerjaan
import com.bara.recapitulation.util.getUserId
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.isNull
import com.inyongtisto.myhelper.extension.toMultipartBody
import com.inyongtisto.myhelper.extension.toastError
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CreatePekerjaanUserActivity : AppCompatActivity() {
    private val viewModel: CreatePekerjaanUserViewModel by viewModel()
    lateinit var binding: ActivityCreatePekerjaanUserBinding
    private var fileImage: File? = null
//    val idPekerjaan = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePekerjaanUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var idPekerjaan = intent.getStringExtra("id_pekerjaan")
        mainButton()
    }

    override fun onResume() {
        super.onResume()
        myDropdown()
    }


    private fun myDropdown() {
        val tipe = resources.getStringArray(R.array.tipe_kerja)
        val arrayAdapter = ArrayAdapter(applicationContext, R.layout.tipe_item_dropdown, tipe)

        binding.inputAutoCompleteTipe.setAdapter(arrayAdapter)
    }

    private fun mainButton() {
        binding.dashboardDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.uploadImageTask.setOnClickListener {
            picImage()
        }

        binding.btnCreateTask.setOnClickListener {
            createPekerjaan()

        }

        myCalendar()
    }


    private fun picImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(3080, 3080, true)
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!
            fileImage = File(uri.path!!)
            Picasso.get().load(fileImage!!).into(binding.taskImage)
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
        binding.inputTanggal.setText(myCalendar?.time?.let { sdf.format(it) })
    }

    private fun createPekerjaan() {
        if (binding.inputAutoCompleteTipe.text.toString().equals("Tipe pekerjaan")
            || binding.inputJudulTask.isEmpty()
            || binding.inputJamKerja.isEmpty()
            || binding.inputTanggal.isEmpty()
            || binding.inputDeskripsiTask.isEmpty()) return

        val reqData = DetailPekerjaan(
            id_user = getUserId(),
            id_pekerjaan = intent.getStringExtra("id_pekerjaan"),
            tipe = binding.inputAutoCompleteTipe.text.toString(),
            nama_pekerjaan = binding.inputJudulTask.text.toString(),
            jam_kerja = binding.inputJamKerja.text.toString(),
            tgl_kerja = binding.inputTanggal.text.toString(),
            desc_pekerjaan = binding.inputDeskripsiTask.text.toString(),

            )

//        val file = fileImage.toMultipartBody()

        viewModel.createPekerjaanUser(reqData).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
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
}