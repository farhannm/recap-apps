package com.bara.recapitulation.core.data.source.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailPekerjaan(
    val bukti_pekerjaan: String? = null,
    val created_at: String? = null,
    val desc_pekerjaan: String? = null,
    val id: Int? = null,
    val hari_ini: String? = null,
    val id_pekerjaan: Int? = null,
    val id_user: Int? = null,
    val jam_kerja: String? = null,
    val nama_pekerjaan: String? = null,
    val tgl_kerja: String? = null,
    val tipe: String? = null,
    val updated_at: String? = null
) : Parcelable