package com.ifs21028.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifs21028.dinopedia.databinding.ItemRowFamiliBinding

class ListFamiliAdapter(private val listFamili: ArrayList<Famili>) :
    RecyclerView.Adapter<ListFamiliAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFamiliBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val famili = listFamili[position]
        holder.binding.ivItemFamili.setImageResource(famili.icon)
        holder.binding.tvItemFamili.text = famili.name

        // Ambil data dari string-array sesuai dengan famili yang dipilih
//        val dinoArray = when (famili.name) {
//            "Saurischia" -> holder.itemView.context.resources.getStringArray(R.array.famili_saurischia)
////            "Ceratopsia" -> holder.itemView.context.resources.getStringArray(R.array.famili_c)
//            else -> emptyArray() // Tambahkan case untuk famili lain jika diperlukan
//        }
//
//        // Buat daftar objek Dino dari data string-array
//        val dinoList = ArrayList<Dino>()
//        for (i in 0 until dinoArray.size step 9) {
//            val dino = Dino(
//                dinoArray[i], // Nama
//                dinoArray[i + 1], // Deskripsi
//                dinoArray[i + 2], // Karakteristik
//                dinoArray[i + 3], // Jenis Makanan
//                dinoArray[i + 4], // Periode Hidup
//                dinoArray[i + 5], // Habitat
//                dinoArray[i + 6], // Perilaku
//                dinoArray[i + 7], // Ukuran
//                dinoArray[i + 8] // Kelemahan
//            )
//            dinoList.add(dino)
//        }
//
//        // Buat adapter untuk inner RecyclerView
//        val innerAdapter = DinoAdapter(dinoList)
//
//        // Implementasikan nested RecyclerView di dalam adapter utama
//        holder.binding.innerRecyclerView.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            adapter = innerAdapter
//        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFamili[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listFamili.size

    class ListViewHolder(var binding: ItemRowFamiliBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Famili)
    }
}