package com.example.kotlinfuel_managment.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.example.kotlinfuel_managment.databinding.ActivityLauncherBinding

class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val sharedPreference =  getSharedPreferences("Launcher", Context.MODE_PRIVATE)
            sharedPreference.edit() {
                this.putBoolean("flag",true)
                this.apply()
            }

            startActivity(Intent(this@LauncherActivity, HomeActivity::class.java))
            finish()
        }
    }
}