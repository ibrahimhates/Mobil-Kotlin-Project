package com.example.mobil_kotlin_project

class Users {
    var UserName: String
        get
        private set
    var correct: Int = 0
        get
        private set
    var best: Int = 0
        get
        private set

    constructor(UserName:String){
        this.UserName = UserName
    }
    //Eger Oyun Bittiyse bu method Cagirilir ve gerekli scor duzenlemesi yapilir.
    fun isGameOver(){
        if(best<correct)
            best = correct
        correct = 0
    }
    //  Dogru sayisini artirir.
    fun setCorrect(){
        correct++;
    }


}