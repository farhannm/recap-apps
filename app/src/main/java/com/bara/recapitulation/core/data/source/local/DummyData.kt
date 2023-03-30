package com.bara.recapitulation.core.data.source.local

import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import com.bara.recapitulation.core.data.source.model.User

object DummyData {
    val listUser = listOf(
        User(id = 1, id_role = 2, nama = "Farhan Maulana", email = "farhan@gmail.com", jabatan = "Internship", alamat = "Osaka, Japan", telp = "092939129321"),
        User(id = 3, id_role = 2, nama = "Eren Yeager", email = "eren@gmail.com", jabatan = "Main Char", alamat = "Paradise Island", telp = "none"),
        User(id = 4, id_role = 2, nama = "Karim Basu", email = "karim.basu@gmail.com", jabatan = "Senior", alamat = "Manager", telp = "03490241421"),
        User(id = 5, id_role = 2, nama = "Nazriel Irham", email = "ariel@gmail.com", jabatan = "Vocal", alamat = "Citeureup", telp = "01110241421"),
    )

    val listPekerjaan = listOf(
        Pekerjaan(id = 1, id_user = 1, bulan = "Januari", total_jam = "157", jam_toleransi = "20", start = "2023-01-01 00:00:00", end = "2023-01-31 00:00:00"),
        Pekerjaan(id = 2, id_user = 1, bulan = "Februari", total_jam = "160", jam_toleransi = "23", start = "2023-02-01 00:00:00", end = "2023-02-28 00:00:00"),
        Pekerjaan(id = 3, id_user = 1, bulan = "Maret", total_jam = "163", jam_toleransi = "22", start = "2023-03-01 00:00:00", end = "2023-03-31 00:00:00"),
    )

    val listDetailPekerjaan = listOf(
        DetailPekerjaan(id = 1, id_pekerjaan = 1, tipe = "Proggress weekday", nama_pekerjaan = "Fetch Api", jam_kerja = "8 jam", tgl_kerja = "2023-02-01", desc_pekerjaan = "Implementasi dari API yang telah dibuat."),
//        DetailPekerjaan(id = 2, id_pekerjaan = 1, tipe = "Lembur weekend", nama_pekerjaan = "Fetch Api#2", jam_kerja = "8 jam", tgl_kerja = "2023-02-02", desc_pekerjaan = "Get current user token."),
//        DetailPekerjaan(id = 3, id_pekerjaan = 1, tipe = "Lembur weekday", nama_pekerjaan = "Bug Fixing ", jam_kerja = "3 jam", tgl_kerja = "2023-02-04", desc_pekerjaan = "Fix the errror and bugs."),
    )
}