package com.example.quizapp

//Las data class nos sirven para realizar tareas muy concretas y las utilizamos mas solo para
//guardar datos
data class Question(val id: Int, val question: String,val image: Int, val optionOne: String, val optionTwo: String, val optionThree: String, val optionFour: String,val correctAnswer: Int)