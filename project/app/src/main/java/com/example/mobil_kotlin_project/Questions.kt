package com.example.mobil_kotlin_project

import android.os.CountDownTimer
import kotlin.random.Random

class Questions {
    private var answer: Int = 0
    private var index: Int = 0
    fun getAnswer():Int{
        return answer
    }

    fun createQuestion():String{
        var oprList = arrayListOf("+","-","x","/")
        index = Random.nextInt(0,4)

        var firstNum: Int
        var secondNum: Int

        if(index==3){
            secondNum = Random.nextInt(1,20)
            firstNum = bolmeSorusuSorusu(secondNum)
        }else{
            firstNum = Random.nextInt(1,50)
            secondNum = Random.nextInt(1,50)
        }

        answer = processByOpr(firstNum,secondNum,index)

        return "$firstNum ${oprList[index]} $secondNum"
    }

    private fun bolmeSorusuSorusu(secondNum: Int):Int{
        var firstNum = Random.nextInt(1,20)*secondNum
        return firstNum
    }

    private fun processByOpr(firstNum:Int,secondNum:Int,index:Int):Int{
        if(index==0)
            return (firstNum+secondNum)
        else if(index==1)
            return (firstNum-secondNum)
        else if(index==2)
            return (firstNum*secondNum)
        else
            return (firstNum/secondNum)
    }

}
























