package com.example.rafifapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import com.example.rafifapps.AuthActivity
import com.example.rafifapps.Home.pertemuan_10.TenthActivity
import com.example.rafifapps.Home.pertemuan_13.ThirteenthActivity
import com.example.rafifapps.Home.pertemuan_2.SecondActivity
import com.example.rafifapps.Home.pertemuan_3.ThirdActivity
import com.example.rafifapps.Home.pertemuan_3.ThirdResultActivity
import com.example.rafifapps.Home.pertemuan_4.FourthActivity
import com.example.rafifapps.Home.pertemuan_5.FifthActivity
import com.example.rafifapps.Home.pertemuan_7.SevenActivity
import com.example.rafifapps.Home.pertemuan_9.NinthActivity
import com.example.rafifapps.data.api.CatFactApiClient
import com.example.rafifapps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        /** Setup Toolbar */
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        /** Navigasi Pertemuan 2 */
        binding.btnPertemuan2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        /** Navigasi Pertemuan 3 */
        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        /** Navigasi Pertemuan 4 */
        binding.btnKirim.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        /** Navigasi Pertemuan 5 */
        binding.btnToFive.setOnClickListener {
            // Koreksi: Mengarah ke FifthActivity
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }

        /** Navigasi Pertemuan 7 */
        binding.btnToSeven.setOnClickListener {
            startActivity(Intent(requireContext(), SevenActivity::class.java))
        }

        /** Navigasi Pertemuan 9 */
        binding.btnPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        /** Navigasi Pertemuan 10 */
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        binding.btnPertemuan13.setOnClickListener {
            startActivity(Intent(requireContext(), ThirteenthActivity::class.java))
        }

        /** Logout Logic */
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    sharedPref.edit { clear() }
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
        loadCatFact()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }
    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}