package com.ifs21028.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifs21028.dinopedia.databinding.ActivityDetdinoBinding

class DetdinoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetdinoBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetdinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DETDINO, Dino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DETDINO)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Tentang ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    private fun loadData(dino: Dino) {
        binding.ivDetailIcon.setImageResource(dino.icon)
        binding.tvDetailName.text = dino.name
        binding.tvDetailDescription.text = dino.description
        binding.tvDetailCharacteristic.text = dino.characteristics
        binding.tvDetailGroup.text = dino.group
        binding.tvDetailHabitat.text = dino.habitat
        binding.tvDetailFood.text = dino.foodType
        binding.tvDetailLength.text = dino.length
        binding.tvDetailHeight.text = dino.height
        binding.tvDetailWeight.text = dino.weight
        binding.tvDetailWeak.text = dino.weakness
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.menu_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "${dino!!.name}, ${dino!!.description}")
                startActivity(Intent.createChooser(shareIntent, "Share Via"))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DETDINO = "extra_detdino"
    }
}

