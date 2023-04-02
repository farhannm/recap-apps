package com.bara.recapitulation.ui.Dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bara.recapitulation.core.data.source.model.DetailPekerjaan
import com.bara.recapitulation.databinding.ListDetailPekerjaanBinding
import com.bara.recapitulation.ui.Recap.RecapAdmin.Recap.DetailRecapTaskUser.DetailRecapTaskUserActivity
import com.inyongtisto.myhelper.extension.intentActivity

@SuppressLint("NotifyDataSetChanged")
class DetailPekerjaanAdapter : RecyclerView.Adapter<DetailPekerjaanAdapter.ViewHolder>(){

    private var data = ArrayList<DetailPekerjaan>()

    inner class ViewHolder(private val itemBinding : ListDetailPekerjaanBinding) : RecyclerView.ViewHolder(itemBinding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item : DetailPekerjaan, position: Int){
            itemBinding.apply {
                txtJudulTask.text = item.nama_pekerjaan
                txtTipePekerjaan.text = item.tipe
                txtLamaJam.text = "${item.jam_kerja} jam"
                txtIdDetailPk.text = item.id.toString()

                val idDetailPk = item.id.toString()
                println("id pekerjaan $idDetailPk")

                layoutHolder.setOnClickListener {
                    root.context.intentActivity(DetailRecapTaskUserActivity::class.java, item)
                }
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