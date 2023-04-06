package com.bara.recapitulation.ui.Dashboard.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bara.recapitulation.R
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.databinding.ListUserBinding
import com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.DetailRecapAdminActivity
import com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.DetailRecapTaskUser.DetailRecapTaskUserActivity
import com.bara.recapitulation.ui.Recap.RecapUser.Recap.DetailRecapUserActivity
import com.inyongtisto.myhelper.extension.intentActivity

@SuppressLint("NotifyDataSetChanged")
class UserAdminAdapter : RecyclerView.Adapter<UserAdminAdapter.ViewHolder>(){

    private var data = ArrayList<User>()

    inner class ViewHolder(private val itemBinding : ListUserBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item : User, position: Int){
            itemBinding.apply {
                val imageLink = item.image

                txtKaryawanName.text = item.nama
                txtJabatanKaryawan.text = item.jabatan

                if (imageLink != null) {
                    userProfileImage.load(imageLink) {
                        crossfade(true)
                        crossfade(1000)
                    }
                } else {
                userProfileImage.load(R.drawable.icon_profile)
            }

                layoutUser.setOnClickListener {
                    root.context.intentActivity(DetailRecapTaskUserActivity::class.java, item)
                    println("ID pekerjaan adalah ${item.id_pekerjaan}")
                }
            }
        }
    }

    fun addItems(items : List<User>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ListUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

}