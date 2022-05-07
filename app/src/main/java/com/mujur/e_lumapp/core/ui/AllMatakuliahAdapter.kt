package com.mujur.e_lumapp.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mujur.e_lumapp.R
import com.mujur.e_lumapp.core.domain.model.Matakuliah
import com.mujur.e_lumapp.databinding.ItemAllMatakuliahBinding

class AllMatakuliahAdapter : RecyclerView.Adapter<AllMatakuliahAdapter.ListViewHolder>() {

    private var listData = ArrayList<Matakuliah>()
    var onItemClick: ((Matakuliah) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Matakuliah>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_all_matakuliah, parent, false
        )
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAllMatakuliahBinding.bind(itemView)
        fun bind(data: Matakuliah) {
            with(binding) {
                kodeMK.text = data.kodeMK.toString()
                nameMK.text = data.nameMK
                bobot.text = data.bobot.toString()
                semester.text = data.semester.toString()
            }
        }

        init {
            binding.btnAction.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}