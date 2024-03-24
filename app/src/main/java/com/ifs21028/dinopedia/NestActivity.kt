package com.ifs21028.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21028.dinopedia.databinding.ActivityNestBinding

class NestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNestBinding
    private val dataDino = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dino = intent.getStringExtra(EXTRA_DINO)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Dinosaurus $dino"
        } else {
            finish()
        }

        binding.rvDino.setHasFixedSize(false)
        dataDino.addAll(getListDino(dino!!.lowercase() + "_dino"))
        showRecyclerList()
    }

    private fun openProfile(){
        val intent = Intent(this@NestActivity, ProfileActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_about -> {
                // Tambahkan kode aksi yang diinginkan di sini
                openProfile()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListDino(namaFamili: String): ArrayList<Dino> {
        val listDino = ArrayList<Dino>()

        // Mendapatkan array string berdasarkan nama famili
        val resourceId = resources.getIdentifier(namaFamili, "array", packageName)
        val familiDino = resources.getStringArray(resourceId)
//        val icons = resources.obtainTypedArray(resourceId)

        for (dinoData in familiDino.toList().chunked(11)) {
            val dino = Dino(
                resources.getIdentifier(dinoData[0], "drawable", packageName),       // Icon
                dinoData[1],  // Name
                dinoData[2],  // Description
                dinoData[3],  // Characteristic
                dinoData[4],  // Group
                dinoData[5],  // Habitat
                dinoData[6],  // Food
                dinoData[7],  // Length
                dinoData[8],  // Height
                dinoData[9],   // Weight
                dinoData[10],  // Weakness
            )
            listDino.add(dino)
        }

        return listDino
    }



    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDino.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager = LinearLayoutManager(this)
        }

        val listDinoAdapter = ListDinoAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object : ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@NestActivity, DetdinoActivity::class.java)
        intentWithData.putExtra(DetdinoActivity.EXTRA_DETDINO, dino)
        startActivity(intentWithData)
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}
