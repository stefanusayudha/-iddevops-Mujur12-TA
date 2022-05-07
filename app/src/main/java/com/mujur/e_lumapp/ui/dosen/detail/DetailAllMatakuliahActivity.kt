package com.mujur.e_lumapp.ui.dosen.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mujur.e_lumapp.MainDosenActivity
import com.mujur.e_lumapp.core.domain.model.Matakuliah
import com.mujur.e_lumapp.databinding.ActivityDetailAllMatakuliahBinding
import com.mujur.e_lumapp.ui.dosen.scanner.ScannerActivity


class DetailAllMatakuliahActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }


    private lateinit var binding: ActivityDetailAllMatakuliahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAllMatakuliahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getParcelableExtra<Matakuliah>(EXTRA_DATA)
        detail?.let {
            binding.txtNameMatakuliah.text = detail.nameMK
            binding.txtKodeMatakuliah.text = detail.kodeMK.toString()
        }

        binding.btnBackDetail.setOnClickListener {
            startActivity(Intent(this@DetailAllMatakuliahActivity, MainDosenActivity::class.java))
            finish()
        }

        binding.btnInputPresensi.setOnClickListener {
            val intent = Intent(this@DetailAllMatakuliahActivity, ScannerActivity::class.java)
            startActivity(intent)
        }
    }
}
