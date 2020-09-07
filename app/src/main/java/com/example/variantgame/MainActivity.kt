package com.example.variantgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var firstNumber = 1
    var secondNumber = 1
    var operator = ""
    var calculator = 0
    var total =0
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
 }

    override fun onStart() {
        super.onStart()
        attempt.text = "attempt ${total+1}"
        firstNumber = generateRandomNumber(1, 50)
        secondNumber = generateRandomNumber(1, 20)
        operator = generateRandomOperator()
        if (operator == "/") firstNumber*=secondNumber
        show_operator.text = "$firstNumber $operator $secondNumber"
        setWrongAnswer()
    }


    fun variantClick(answer: View) {
        if ((answer as Button).text == rightAnswer().toString()) { calculator+=1; total+=1} else total+=1
        val intent = Intent(this, MainActivity2::class.java)

        if (total==5) {
            if (calculator==0)
                intent.putExtra("sentTotalResult", "No right answers!!!")
            else  intent.putExtra("sentTotalResult", "$calculator correct answers out of $total")
            total=0
            calculator=0
            startActivity(intent)
        } else {
            attempt.text = "attempt ${total+1}"
            firstNumber = generateRandomNumber(1, 10)
            secondNumber = generateRandomNumber(1, 20)
            operator = generateRandomOperator()

            if (operator == "/") firstNumber*=secondNumber

            show_operator.text = "$firstNumber $operator $secondNumber"
            setWrongAnswer()
        }
    }


    private fun rightAnswer() : Int {
        var san = 0
        when (operator) {
            "+" -> san = firstNumber+secondNumber
            "-" -> san = firstNumber-secondNumber
            "*" -> san = firstNumber*secondNumber
            "/" -> san = firstNumber/secondNumber
        }
        return san
    }


    private fun setWrongAnswer() {
        var a = generateRandomNumber(1, 100)
        btnA.text = a.toString()
        if (a==rightAnswer()) btnA.text = (rightAnswer()+5).toString();

        var b = generateRandomNumber(rightAnswer()+2, rightAnswer()+40)
        btnB.text = b.toString()
        if (b==a || rightAnswer()==b) btnB.text = (rightAnswer()+a).toString()

        var c = generateRandomNumber(10,80)
        btnC.text = c.toString()
        if (c==a || rightAnswer()==c || c==b) btnC.text = (rightAnswer()*a).toString()

        var d = generateRandomNumber(1,90)
        btnD.text = d.toString()
        if (d==a || d==b ||d==c|| rightAnswer()==d) btnD.text = (rightAnswer()*b).toString()

        when (generateRandomNumber(1,5)) {
            1 -> btnA.text = rightAnswer().toString()
            2 -> btnB.text = rightAnswer().toString()
            3 -> btnC.text = rightAnswer().toString()
            4 -> btnD.text = rightAnswer().toString()
            else -> btnA.text = rightAnswer().toString()
        }

    }


    private fun generateRandomNumber(start: Int, end: Int): Int = Random.nextInt(start, end)

    private fun generateRandomOperator() :String {
        val san = Random.nextInt(0, 4)
        var operator =""
        operator = when (san) {
            0 -> "+"
            1 -> "-"
            2 -> "*"
            3 -> "/"
            else -> "+"
        }

        return operator}
    }

