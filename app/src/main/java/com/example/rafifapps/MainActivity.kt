package com.example.rafifapps

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rafifapps.databinding.ActivityFourthBinding
import com.example.rafifapps.databinding.ActivityMainBinding
import com.example.rafifapps.databinding.ActivityThirdBinding
import com.example.rafifapps.pertemuan_3.ThirdResultActivity
import com.example.rafifapps.pertemuan_4.FourthActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnKirim.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)

            /*tambahkan bagian berikut*/
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)

            startActivity(intent)


        }
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)

        }
    }
}