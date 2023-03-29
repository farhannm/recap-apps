package com.bara.recapitulation.ui.Dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bara.recapitulation.core.data.source.model.Pekerjaan
import com.bara.recapitulation.databinding.ListPekerjaanBinding
@SuppressLint("NotifyDataSetChanged")
class PekerjaanAdminAdapter : RecyclerView.Adapter<PekerjaanAdminAdapter.ViewHolder>(){

    private var data = ArrayList<Pekerjaan>()

    inner class ViewHolder(private val itemBinding : ListPekerjaanBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item : Pekerjaan, position: Int){
            itemBinding.apply {
                txtBulan.text = item.bulan
                txtStart.text = item.start
                txtEnd.text = item.end
            }
        }
    }

    fun addItems(items : List<Pekerjaan>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ListPekerjaanBinding.inflate(
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