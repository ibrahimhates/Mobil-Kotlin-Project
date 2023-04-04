package com.example.mobil_kotlin_project

import kotlin.random.Random

class Questions {
    private var answer: Int = 0

    fun createQuestion():String{
        var oprList = arrayListOf<String>("+","-","x","/")
        var index = Random.nextInt(0,4)

        var firstNum = Random.nextInt(1,50)
        var secondNum = Random.nextInt(1,50)

        answer = processByOpr(firstNum,secondNum,index)

        return "$firstNum ${oprList.get(index)} $secondNum"
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

    fun getAnswer():Int{
        return answer
    }
}
























