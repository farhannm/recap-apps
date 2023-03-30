package com.bara.recapitulation.ui.Dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.databinding.ListDetailPekerjaanBinding

@SuppressLint("NotifyDataSetChanged")
class DetailPekerjaanAdapter : RecyclerView.Adapter<DetailPekerjaanAdapter.ViewHolder>(){

    private var data = ArrayList<DetailPekerjaan>()

    inner class ViewHolder(private val itemBinding : ListDetailPekerjaanBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item : DetailPekerjaan, position: Int){
            itemBinding.apply {
                txtJudulTask.text = item.nama_pekerjaan
                txtTipePekerjaan.text = item.tipe
                txtLamaJam.text = item.jam_kerja
            }
        }
    }

    fun addItems(items : List<DetailPekerjaan>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ListDetailPekerjaanBinding.inflate(
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