package com.example.mobil_kotlin_project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.mobil_kotlin_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var  sharedPreferences: SharedPreferences
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBestRepoOrCreate()
        var best = sharedPreferences.getInt("best",0)
        binding.bestInHome.text = best.toString()
    }
    fun playButton(view: View){
        getGamePage()
    }

    fun getGamePage(){
        val gamePage = Intent(applicationContext,SecondPage::class.java)
        startActivity(gamePage)
    }

    fun getBestRepoOrCreate(){
        sharedPreferences = this.getSharedPreferences("BestScores", MODE_PRIVATE)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}