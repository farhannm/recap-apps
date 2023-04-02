package com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.DetailRecapTaskUser

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.core.data.source.remote.network.State
import com.bara.recapitulation.core.data.source.remote.request.DetailPkRequest
import com.bara.recapitulation.databinding.ActivityDetailRecapTaskUserBinding
import com.bara.recapitulation.databinding.ActivityDetailTaskUserBinding
import com.bara.recapitulation.ui.Dashboard.adapter.DetailPekerjaanAdapter
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailRecapTaskUserActivity : AppCompatActivity() {

    private val viewModel: DetailRecapTaskUserViewModel by viewModel()
    lateinit var binding: ActivityDetailRecapTaskUserBinding
    private val detailPk by extra<DetailPekerjaan>()
    private val adapterDetailPekerjaan = DetailPekerjaanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRecapTaskUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun mainButton() {
        binding.recapDest.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

}