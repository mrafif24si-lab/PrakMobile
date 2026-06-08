package com.example.rafifapps.Home.pertemuan_13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.rafifapps.R
import com.example.rafifapps.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar13)
        supportActionBar?.apply {
            title = "Pertemuan 13"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 1. Inisialisasi Adapter yang BARU untuk Pertemuan 13
        val tabsAdapter = ThirteenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Capture"
                    // Menggunakan ic_capture dari drawable Anda
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_capture)
                }
                1 -> {
                    tab.text = "QR Code"
                    // Menggunakan ic_qr dari drawable Anda
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_qr)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                2 -> {
                    tab.text = "Scan"
                    // Menggunakan ic_scan dari drawable Anda
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_scan)
                }
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}