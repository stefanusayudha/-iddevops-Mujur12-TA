package com.mujur.e_lumapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mujur.e_lumapp.R
import com.mujur.e_lumapp.core.domain.model.Matakuliah
import com.mujur.e_lumapp.databinding.ItemListMatakuliahBinding

class MatakuliahAdapter : RecyclerView.Adapter<MatakuliahAdapter.ListViewHolder>() {

    private var listData = ArrayList<Matakuliah>()

    fun setData(newListData: List<Matakuliah>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_matakuliah, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMatakuliahBinding.bind(itemView)

        fun bind(data: Matakuliah) {
            with(binding) {
                kodeMK.text = data.kodeMK.toString()
                nameMK.text = data.nameMK
                semester.text = data.semester.toString()
            }
        }
    }
}