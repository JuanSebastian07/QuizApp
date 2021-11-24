package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat


//La clase extiende de dos clases de AppCompatActivity() y de View.OnClickListener para eso View.OnClickListener debe ser una interface para que una clase pueda heredar de dos super clases
class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    //Variables Globales
    private var mCurrentPosition: Int = 1
    //Esta variable puede llegar a tomar un valor null
    private var mQuestionList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers: Int = 0
    //
    private var mUserName : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        //Recuperamos los datos del MainActivity
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        //Accedemos al metodo getQuestion del objeto Constants el cual va retornar un ArrayList de tipo
        //Question
        mQuestionList = Constants.getQuestions()

        setQuestion()

        //Esto tiene que ir siempre con la funcion override fun onClick..
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }


    //esta funcion nos permite fijar las preguntas y de mas en el activity_quiz_question.xml
    private fun setQuestion(){

        val barraProgeso = findViewById<ProgressBar>(R.id.progressBarr)
        val textViewBarraProgeso = findViewById<TextView>(R.id.tv_progress)
        val tv_question  = findViewById<TextView>(R.id.tv_question)
        val iv_image = findViewById<ImageView>(R.id.iv_image)
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        //mCurrentPosition = 1
        //Aqui ya no necesitamos declarar el tipo de la variable question por que ya nos aseguramos que no va ser null con !! y arriba escribimos que la varibale puede ser nulll
        // y para decir que una variable puede ser null tendremos que escribir de que tipo es la varibales y ponerle un '?' al tipo
        val question = mQuestionList!![mCurrentPosition-1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionList!!.size){
            btn_submit.text = "Final"
        }else{
            btn_submit.text = "Enviar"
        }

        //Acedemos a la propiedad progress de progressBarr y le ponemos un nuevo valor
        barraProgeso.progress = mCurrentPosition
        textViewBarraProgeso.text = "${mCurrentPosition}/${barraProgeso.max}"

        //!! la usasmos cuando nos aseguramos que la variable no es null
        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }

    //esta funcion nos permite darle otro tipo de apariencia a las pregntas no seleccionadas
    private fun defaultOptionsView(){

        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)

        //Aqui agregamos valores al array options en cierta posicion
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)


        for (option in options){
            //Example of Parsear, more here https://carloszr.com/parsear-java-metodo-parse/
            // cadena: String = "12.3";
            // decimal:double = Double.parseDouble(cadena3);
            //Parseamos el colorString a Color y se lo fijamos a TextColor
            option.setTextColor(Color.parseColor("#9F9F9F"))
            option.typeface = Typeface.DEFAULT
            //le asignamos el backgroud por default que hicimos nosotros mismos
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)

        }
    }

    //override para sobrescribir una funcion a la cual extendemos, esto esta en el tema de las herencias
    //sobrescribimos la funcion OnClick()
    //Esto para que el boton al momento de seleccionarlo quede seleccionado
    //esta funcion se ejecutara cada vez que demos un click
    override fun onClick(p0: View?) {

        val tv_option_on = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_tw = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_thre = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_fou = findViewById<TextView>(R.id.tv_option_four)
        val btn_submi = findViewById<Button>(R.id.btn_submit)

        //cuando el boton seleccionado P0?.id == R.id.tv_option_one poner verde caso 1
        //cuando el boton seleccionado P0?.id == R.id.tv_option_two poner verde caso 2
        //  .
        //  .
        when(p0?.id){
            R.id.tv_option_one -> {selectedOptionView(tv_option_on,1)}
            R.id.tv_option_two -> {selectedOptionView(tv_option_tw,2)}
            R.id.tv_option_three -> {selectedOptionView(tv_option_thre,3)}
            R.id.tv_option_four -> {selectedOptionView(tv_option_fou,4)}
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0){
                mCurrentPosition ++
                when{
                    mCurrentPosition <= mQuestionList!!.size -> {setQuestion()}
                    else ->{
                        //Toast.makeText(this,"Haz completado exitosamente el Quiz",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME,mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTION, mQuestionList!!.size)
                        startActivity(intent)
                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btn_submi.text = "Final"
                    }else{
                        btn_submi.text = "Ir a la siguiente Pregunta"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }


    private fun answerView(answer:Int, drawableView:Int){
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        when(answer){
            1->{tv_option_one.background = ContextCompat.getDrawable(this,drawableView)}
            2->{tv_option_two.background = ContextCompat.getDrawable(this,drawableView)}
            3->{tv_option_three.background = ContextCompat.getDrawable(this,drawableView)}
            4->{tv_option_four.background = ContextCompat.getDrawable(this,drawableView)}

        }
    }

    //opcion Seleccionada
    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#43F423"))//verde
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        //le asignamos el backgroud por default que hicimos nosotros mismos
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }

}//clase
