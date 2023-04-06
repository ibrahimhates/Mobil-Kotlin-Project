package com.example.mobil_kotlin_project

import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.view.View
import com.example.mobil_kotlin_project.databinding.ActivitySecondPageBinding
import java.util.Objects
import kotlin.random.Random

class Manager {
    private var binding: ActivitySecondPageBinding
    private var questions: Questions
    private var sharedPreferences: SharedPreferences
    private var context: Context
    private lateinit var countDownTimer: CountDownTimer
    constructor(binding:ActivitySecondPageBinding,sharedPreferences: SharedPreferences,context: Context){
        this.binding = binding
        questions = Questions()
        this.sharedPreferences = sharedPreferences
        this.context = context
    }

    fun GameStarted(){
        setBestScore()
        startQ()
        countDownTimer = object : CountDownTimer(30000,1000){
            override fun onTick(count: Long) {
                binding.count.text = ((count)/1000+1).toString()
            }
            override fun onFinish() {
                binding.count.text = "0"
                binding.question.text = "0"
                setBestScore()
                var score = binding.correct.text.toString().toInt()
                var best = sharedPreferences.getInt("best",0);
                dialogShow(score,best)
            }
        }
        countDownTimer.start()
    }

    private fun delay(){
        object : CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.cevap1.isEnabled = false
                binding.cevap2.isEnabled = false
                binding.cevap3.isEnabled = false
            }
            override fun onFinish() {
                binding.cevap1.isEnabled = true
                binding.cevap2.isEnabled = true
                binding.cevap3.isEnabled = true
            }
        }.start()
    }

    private fun startQ(){
        delay()
        writeScreen(questions.createQuestion())
        writeAnswers(questions.getAnswer())
        answerController()
    }

    private fun setBestScore(){
        var best = sharedPreferences.getInt("best",0)
        var correct = binding.correct.text.toString().toInt()
        val edit = sharedPreferences.edit()
        edit.putInt("best",getHighScore(best,correct))
        edit.apply()
    }

    private fun writeScreen(question:String){
        binding.question.text = question.toString()
    }

    private fun writeAnswers(corretAnswer:Int){
        var rand:Int
        if(questions.getIndex()==3){
            rand = Random.nextInt(1,7)
        }
        else
            rand = Random.nextInt(1,20)

        var place = Random.nextInt(0,3)

        if(place == 0){
            binding.cevap1.text = corretAnswer.toString()
            binding.cevap2.text = (corretAnswer+rand).toString()
            binding.cevap3.text = (corretAnswer-rand).toString()
        }
        else if(place == 1){
            binding.cevap2.text = corretAnswer.toString()
            binding.cevap1.text = (corretAnswer-rand).toString()
            binding.cevap3.text = (corretAnswer+rand).toString()
        }
        else{
            binding.cevap3.text = corretAnswer.toString()
            binding.cevap1.text = (corretAnswer+rand).toString()
            binding.cevap2.text = (corretAnswer-rand).toString()
        }
    }

    private fun answerController(){
        binding.cevap1.setOnClickListener(View.OnClickListener {
            if(binding.cevap1.text.equals(questions.getAnswer().toString())){
                var correct:Int = binding.correct.text.toString().toInt()
                correct++
                binding.correct.text = correct.toString()
            }
            startQ()
        })
        binding.cevap2.setOnClickListener(View.OnClickListener {
            if(binding.cevap2.text.equals(questions.getAnswer().toString())){
                var correct:Int = binding.correct.text.toString().toInt()
                correct++
                binding.correct.text = correct.toString()
            }
            startQ()
        })
        binding.cevap3.setOnClickListener(View.OnClickListener {
            if(binding.cevap3.text.equals(questions.getAnswer().toString())){
                var correct:Int = binding.correct.text.toString().toInt()
                correct++
                binding.correct.text = correct.toString()
            }
            startQ()
        })
    }

    private fun getHighScore(best: Int,correct: Int):Int{
        if(correct>best)
            return correct
        return best
    }

    private fun dialogShow(score: Int,best:Int){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("GAME OVER")
        builder.setMessage("YOUR SCORE ${score} AND BEST ${best}")

        builder.setPositiveButton("Tamam") { dialog, which ->
            getMainPage()
        }
        builder.setCancelable(false)

        val dialog: AlertDialog = builder.create()
        dialog.show()
        countDownTimer.cancel()
    }

    private fun getMainPage(){
        val secondPage = Intent(context.applicationContext,MainActivity::class.java)
        context.startActivity(secondPage)
    }
}