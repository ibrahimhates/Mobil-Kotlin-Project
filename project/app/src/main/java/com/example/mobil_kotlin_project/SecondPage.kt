package com.example.mobil_kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.mobil_kotlin_project.databinding.ActivitySecondPageBinding

class SecondPage : AppCompatActivity() {
    private lateinit var binding: ActivitySecondPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var user = MainActivity.user
        if(user != null)
            binding.textView.text = "Hosgeldin: \n${user.UserName}"
    }
    /*
    fun backButton(view: View){
        val mainPage = Intent(applicationContext,MainActivity::class.java)
        startActivity(mainPage)
    }
    fun Deneme(){
        var obje =  object : CountDownTimer(15000,1000){
            override fun onTick(count: Long) {
                binding.textView.text = "Sure => ${count/1000 + 1}"
            }

            override fun onFinish() {
                binding.textView.text = "Sure Bitti"
            }
        }
        obje.start()
    }
     */

}