package com.bara.recapitulation.core.data.source.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pekerjaan(
    val bulan: String? = null,
    val created_at: String? = null,
    val deleted_at: String? = null,
    val end: String? = null,
    val id: Int? = null,
    val id_user: Int? = null,
    val jam_toleransi: String? = null,
    val start: String? = null,
    val total_jam: String? = null,
    val updated_at: String? = null,
    val jumlah_karyawan: String? = null,
    val mulai: String? = null,
    val berakhir: String? = null
) : Parcelable