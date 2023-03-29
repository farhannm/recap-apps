package com.bara.recapitulation.ui.Dashboard.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.databinding.ListKaryawanBinding
import com.bara.recapitulation.ui.Dashboard.DashboardAdmin.Karyawan.KaryawanActivity
import com.squareup.picasso.Picasso
import java.io.File

@SuppressLint("NotifyDataSetChanged")
class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private var data = ArrayList<User>()

    inner class ViewHolder(private val itemBinding : ListKaryawanBinding) : RecyclerView.ViewHolder(itemBinding.root){

        fun bind(item : User, position: Int){
            itemBinding.apply {
                val imageLink = item.image

                if (imageLink != null) {
                    userProfileImage.load(imageLink) {
                        crossfade(true)
                        crossfade(1000)
                    }
                } else {
                    userProfileImage.load(R.drawable.icon_profile)
                }

                txtKaryawanName.text = item.nama
                txtJabatanKaryawan.text = item.jabatan
            }
        }
    }

    fun addItems(items : List<User>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ListKaryawanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
        holder.itemView.setOnClickListener {

        }
    }

}