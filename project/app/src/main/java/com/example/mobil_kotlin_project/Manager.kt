package com.example.mobil_kotlin_project

import android.view.View
import com.example.mobil_kotlin_project.databinding.ActivitySecondPageBinding
import kotlin.random.Random

class Manager {
    private var binding: ActivitySecondPageBinding
    private var questions: Questions
    constructor(bind:ActivitySecondPageBinding){
        binding = bind
        questions = Questions()
    }

    fun startQ(){
        writeScreen(questions.createQuestion())
        writeAnswers(questions.getAnswer())
        answerController()
    }

    private fun writeScreen(question:String){
        binding.question.text = question.toString()
    }

    private fun writeAnswers(corretAnswer:Int){
        var rand = Random.nextInt(1,20)
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
}