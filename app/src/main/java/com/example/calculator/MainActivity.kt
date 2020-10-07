package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric:Boolean=false
    var lastDot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        txtInput.append((view as Button).text)
        lastNumeric=true
    }

    fun onClear(view: View){
        txtInput.text=""
        lastNumeric=false
        lastDot=false
    }

    fun OnDecimal(){
        if(lastNumeric && !lastDot){
            txtInput.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var txtValue = txtInput.text.toString()
            var prefix=""

        try {
            if(txtValue.startsWith("-")){
                prefix="-"
                txtValue=txtValue.substring(1)
            }
            if (txtValue.contains("-")){
                val splitValue=txtValue.split("-")

                var one=splitValue[0]
                var two = splitValue[1]

                if(!prefix.isEmpty()){
                    one=prefix+one
                }
                txtInput.text=removeZeroAfterDecimal((one.toDouble() - two.toDouble()).toString())


            }else if (txtValue.contains("+")){
                val splitValue=txtValue.split("+")

                var one=splitValue[0]
                var two = splitValue[1]

                if(!prefix.isEmpty()){
                    one=prefix+one
                }
                txtInput.text=removeZeroAfterDecimal((one.toDouble()+two.toDouble()).toString())


            }else if (txtValue.contains("*")){
                val splitValue=txtValue.split("*")

                var one=splitValue[0]
                var two = splitValue[1]

                if(!prefix.isEmpty()){
                    one=prefix+one
                }
                txtInput.text=removeZeroAfterDecimal((one.toDouble()*two.toDouble()).toString())


            }else if (txtValue.contains("/")){
                val splitValue=txtValue.split("/")

                var one=splitValue[0]
                var two = splitValue[1]

                if(!prefix.isEmpty()){
                    one=prefix+one
                }
                txtInput.text=removeZeroAfterDecimal((one.toDouble()/two.toDouble()).toString())
            }




        }catch (e:ArithmeticException){

            e.printStackTrace()
        }

    }




    }
    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(txtInput.text.toString())){
            txtInput.append((view as Button).text)
            lastDot=false
            lastNumeric=false
        }
    }

    fun removeZeroAfterDecimal(result: String):String{
        var value=result
        if(value.contains(".0")){
            value=result.substring(0,result.length-2)
        }
        return value
    }

    private fun isOperatorAdded(value:String):Boolean {
        return if (value.startsWith("-")) {
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

}

