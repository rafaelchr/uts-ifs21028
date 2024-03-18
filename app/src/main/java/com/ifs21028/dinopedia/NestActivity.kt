package com.ifs21028.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21028.dinopedia.databinding.ActivityMainBinding

class NestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataFamili = ArrayList<Famili>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFamili.setHasFixedSize(false)
        dataFamili.addAll(getListFamili())
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
            R.id.menu_about -> {
                // Tambahkan kode aksi yang diinginkan di sini
                openProfile()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListFamili(): ArrayList<Famili> {
        val dataName = resources.getStringArray(R.array.famili_name)
        val dataIcon = resources.obtainTypedArray(R.array.famili_icon)
        val dataDescription = resources.getStringArray(R.array.famili_description)
        val dataPeriode = resources.getStringArray(R.array.famili_periode)
        val dataCharacteristic = resources.getStringArray(R.array.famili_characteristic)
        val dataPlace = resources.getStringArray(R.array.famili_place)
        val dataEnvi = resources.getStringArray(R.array.famili_env)
        val dataBehavior = resources.getStringArray(R.array.famili_behavior)
        val dataClassif = resources.getStringArray(R.array.famili_classif)

        val listFamili = ArrayList<Famili>()
        for (i in dataName.indices) {
            val famili = Famili(dataName[i], dataIcon.getResourceId(i, -1), dataDescription[i], dataPeriode[i], dataCharacteristic[i], dataPlace[i], dataEnvi[i], dataBehavior[i], dataClassif[i])
            listFamili.add(famili)
        }
        return listFamili
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamili.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvFamili.layoutManager = LinearLayoutManager(this)
        }

        val listFamiliAdapter = ListFamiliAdapter(dataFamili)
        binding.rvFamili.adapter = listFamiliAdapter
        listFamiliAdapter.setOnItemClickCallback(object : ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Famili) {
                showSelectedFamili(data)
            }
        })
    }

    private fun showSelectedFamili(famili: Famili) {
        val intentWithData = Intent(this@NestActivity, DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILI, famili)
        startActivity(intentWithData)
    }
}
