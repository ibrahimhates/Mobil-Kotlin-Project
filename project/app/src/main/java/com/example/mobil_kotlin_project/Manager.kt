package com.example.mobil_kotlin_project

import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.view.View
import com.example.mobil_kotlin_project.databinding.ActivitySecondPageBinding
import kotlin.random.Random

class Manager {
    private var binding: ActivitySecondPageBinding
    private var questions: Questions
    private var sharedPreferences: SharedPreferences
    private var context: Context

    constructor(bind:ActivitySecondPageBinding,sharedPreferences: SharedPreferences,context: Context){
        binding = bind
        questions = Questions()
        this.sharedPreferences = sharedPreferences
        this.context = context
    }

    fun GameStarted(){
        setBestScore()
        startQ()
        var obje =  object : CountDownTimer(60000,1000){
            override fun onTick(count: Long) {
                binding.count.text = ((count)/1000+1).toString()
            }
            override fun onFinish() {
                binding.count.text = "0"
                binding.question.text = "0"
                setBestScore()
                var best = binding.best.text.toString().toInt()
                var score = binding.correct.text.toString().toInt()
                dialogShow(score,best)
            }
        }
        obje.start()
    }

    private fun startQ(){
        writeScreen(questions.createQuestion())
        writeAnswers(questions.getAnswer())
        answerController()
    }

    private fun setBestScore(){
        var best = sharedPreferences.getInt("bst",0)
        binding.best.text = best.toString()
        var correct = binding.correct.text.toString().toInt()
        val edit = sharedPreferences.edit()
        edit.putInt("bst",getHighScore(best,correct))
        edit.apply()
        println("best ${best} correct $correct gethighscore ${getHighScore(best,correct)}")
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
            val secondPage = Intent(context.applicationContext,MainActivity::class.java)
            context.startActivity(secondPage)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}