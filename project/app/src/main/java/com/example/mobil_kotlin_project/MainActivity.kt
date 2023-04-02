package com.example.mobil_kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.mobil_kotlin_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    companion object {
        var user: Users? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun playButton(view: View){
        if(binding.userName.text.toString().isNullOrEmpty()){
            binding.failMessage.text = "Please enter username"
        }
        else if(binding.userName.text.toString().length < 3){
            binding.failMessage.text = "Username cannot be less than 3 characters."
        }
        else{
            MainActivity.user = Users(binding.userName.text.toString())
            getGamePage()
        }
    }

    fun getGamePage(){
        val mainPage = Intent(applicationContext,SecondPage::class.java)
        startActivity(mainPage)
    }
}