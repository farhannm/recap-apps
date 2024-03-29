package com.bara.recapitulation.core.data.source.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val alamat: String? = null,
    val created_at: String? = null,
    val deleted_at: String? = null,
    val email: String? = null,
    val id: Int? = null,
    val id_role: Int? = null,
    val image: String? = null,
    val nama: String? = null,
    val password: String? = null,
    val jabatan: String? = null,
    val token: String? = null,
    val telp: String? = null,
    val updated_at: String? = null,
    val hari_ini: String? = null,
    val id_pekerjaan: Int? = null
) : Parcelable