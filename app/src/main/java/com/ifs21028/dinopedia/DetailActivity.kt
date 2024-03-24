package com.ifs21028.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifs21028.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var famili: Famili? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        famili = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILI, Famili::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }

        val familiName = famili!!.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (famili != null) {
            supportActionBar?.title = "Tentang $familiName"
            loadData(famili!!)
        } else {
            finish()
        }

        val btnDino = findViewById<Button>(R.id.btnDino)

        btnDino.setOnClickListener {
            val intentWithData = Intent(this@DetailActivity, NestActivity::class.java)
            intentWithData.putExtra(NestActivity.EXTRA_DINO, familiName)
            startActivity(intentWithData)
        }

    }

    private fun loadData(famili: Famili) {
        binding.ivDetailIcon.setImageResource(famili.icon)
        binding.tvDetailName.text = famili.name
        binding.tvDetailDescription.text = famili.description
        binding.tvDetailPeriode.text = famili.place
        binding.tvDetailCharacteristic.text = famili.characteristic
        binding.tvDetailPlace.text = famili.place
        binding.tvDetailEnvi.text = famili.envi
        binding.tvDetailBehavior.text = famili.behavior
        binding.tvDetailClassif.text = famili.classif
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.menu_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "${famili!!.name}, ${famili!!.description}")
                startActivity(Intent.createChooser(shareIntent, "Share Via"))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }

}

