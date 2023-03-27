package com.bara.recapitulation.ui.Dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bara.recapitulation.core.data.source.model.User
import com.bara.recapitulation.databinding.ListKaryawanBinding

@SuppressLint("NotifyDataSetChanged")
class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private var data = ArrayList<User>()

    inner class ViewHolder(private val itemBinding : ListKaryawanBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item : User, position: Int){
            itemBinding.apply {
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
    }

}