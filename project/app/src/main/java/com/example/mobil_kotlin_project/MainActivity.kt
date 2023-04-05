package com.example.mobil_kotlin_project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
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
        var best = sharedPreferences.getInt("bst",0)
        binding.bestInHome.text = best.toString()
    }
    fun playButton(view: View){
        getGamePage()
    }

    fun getGamePage(){
        val secondPage = Intent(applicationContext,SecondPage::class.java)
        startActivity(secondPage)
    }

    fun getBestRepoOrCreate(){
        sharedPreferences = this.getSharedPreferences("BestScores", MODE_PRIVATE)
    }
}