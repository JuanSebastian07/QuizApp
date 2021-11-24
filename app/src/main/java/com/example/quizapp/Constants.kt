package com.example.quizapp

//no hay necesidad de instanciarla para acceder a sus valores
object Constants{

    //Ambos val y const son inmutables. const se usa para declarar constantes de tiempo de compilación, mientras que val para constantes de tiempo de ejecución.
    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTION : String = "total_question"
    const val CORRECT_ANSWERS : String = "correct_answers"

    //retornara un ArrayList esta funcion de tipo Question
    fun getQuestions(): ArrayList<Question>{

        val questionsList = ArrayList<Question>()
        //instancia#1 de la clase Question
        val que1 = Question(1,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_argentina,"Argentina","Australia","Belgica","Colombia",1)
        //Agregamos
        questionsList.add(que1)

        //instancia#2 de la clase Question
        val que2 = Question(2,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_australia,"Argentina","Australia","Belgica","Colombia",2)
        questionsList.add(que2)

        //instancia#3 de la clase Question
        val que3 = Question(3,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_belgium,"Argentina","Belgica","Belgica","Colombia",2)
        questionsList.add(que3)

        //instancia#4 de la clase Question
        val que4 = Question(4,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_brazil,"Argentina","Australia","Belgica","Brasil",4)
        questionsList.add(que4)

        //instancia#5 de la clase Question
        val que5 = Question(5,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_denmark,"Dinamarca","Australia","Belgica","Colombia",1)
        questionsList.add(que5)

        //instancia#6 de la clase Question
        val que6 = Question(6,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_india,"India","Australia","Belgica","Colombia",1)
        questionsList.add(que6)

        //instancia#7 de la clase Question
        val que7 = Question(7,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_fiji,"Fiyi","Australia","Belgica","Colombia",1)
        questionsList.add(que7)

        //instancia#2 de la clase Question
        val que8 = Question(8,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_germany,"Argentina","Australia","Alemania","Colombia",3)
        questionsList.add(que8)

        //instancia#2 de la clase Question
        val que9 = Question(9,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_kuwait,"Argentina","Australia","Belgica","Kuwait",4)
        questionsList.add(que9)

        //instancia#2 de la clase Question
        val que10 = Question(10,"De que pais es la bandera de arriba",R.drawable.ic_flag_of_new_zealand,"Argentina","Nueva Zelanda","Belgica","Colombia",2)
        questionsList.add(que10)

        return questionsList
    }
}
