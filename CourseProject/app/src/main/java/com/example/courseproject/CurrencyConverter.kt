package com.example.courseproject

import android.content.Context
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_preferences.view.*
import kotlinx.android.synthetic.main.activity_secondary.*
import kotlinx.android.synthetic.main.activity_secondary.view.*
import java.text.DecimalFormat

// class CurrencyConverter(var income: Double, var textView: TextView? = null, var radioGroup: RadioGroup? = null) {
// public var convertedIncome: Double = 0.0
//
// init {
// conversion()
// convertFormat()
// }
//
// public fun convertFormat(){
// when (radioGroup?.checkedRadioButtonId){
// radioGroup?.usdRadioButton?.id -> textView?.text = DecimalFormat("$##,###.##").format(convertedIncome)
// radioGroup?.eurRadioButton2?.id -> textView?.text = DecimalFormat("€##,###.##").format(convertedIncome)
// radioGroup?.gbpRadioButton3?.id -> textView?.text = DecimalFormat("£##,###.##").format(convertedIncome)
// radioGroup?.cadRadioButton4?.id -> textView?.text = DecimalFormat("$##,###.##").format(convertedIncome)
// radioGroup?.jpyRadioButton5?.id -> textView?.text = DecimalFormat("¥##,###.##").format(convertedIncome)
// radioGroup?.audRadioButton6?.id -> textView?.text = DecimalFormat("$##,###.##").format(convertedIncome)
// }
//
// convertedIncome = 0.0
// }
//
// public fun conversion(income: Double = this.income, fromCurrency: String? = "", toCurrency: String? = ""){
// if(fromCurrency?.uppercase() == "USD"){
// when(toCurrency?.uppercase()){
// "EUR" -> convertedIncome = income.times(0.96)
// "GBP" -> convertedIncome = income.times(0.81)
// "CAD" -> convertedIncome = income.times(1.29)
// "AUD" -> convertedIncome = income.times(1.44)
// "JPY" -> convertedIncome = income.times(129.30)
// else -> convertedIncome = income.times(1.0)
// }
// }
// else if(fromCurrency?.uppercase() == "EUR"){
// when(toCurrency?.uppercase()){
// "USD" -> convertedIncome = income.times(1.04)
// "GBP" -> convertedIncome = income.times(0.85)
// "CAD" -> convertedIncome = income.times(1.34)
// "AUD" -> convertedIncome = income.times(1.50)
// "JPY" -> convertedIncome = income.times(134.56)
// else -> convertedIncome = income.times(1.0)
// }
// }
// else if(fromCurrency?.uppercase() == "GBP"){
// when(toCurrency?.uppercase()){
// "USD" -> convertedIncome = income.times(1.22)
// "EUR" -> convertedIncome = income.times(1.17)
// "CAD" -> convertedIncome = income.times(1.58)
// "AUD" -> convertedIncome = income.times(1.76)
// "JPY" -> convertedIncome = income.times(158.24)
// else -> convertedIncome = income.times(1.0)
// }
// }
// else if(fromCurrency?.uppercase() == "CAD"){
// when(toCurrency?.uppercase()){
// "USD" -> convertedIncome = income.times(0.77)
// "EUR" -> convertedIncome = income.times(0.74)
// "GBP" -> convertedIncome = income.times(0.63)
// "AUD" -> convertedIncome = income.times(1.11)
// "JPY" -> convertedIncome = income.times(100.05)
// else -> convertedIncome = income.times(1.0)
// }
// }
// else if(fromCurrency?.uppercase() == "AUD"){
// when(toCurrency?.uppercase()){
// "USD" -> convertedIncome = income.times(0.69)
// "EUR" -> convertedIncome = income.times(0.66)
// "GBP" -> convertedIncome = income.times(0.56)
// "CAD" -> convertedIncome = income.times(0.89)
// "JPY" -> convertedIncome = income.times(89.60)
// else -> convertedIncome = income.times(1.0)
// }
// }
// else if(fromCurrency?.uppercase() == "JPY"){
// when(toCurrency?.uppercase()){
// "USD" -> convertedIncome = income.times(0.0077)
// "EUR" -> convertedIncome = income.times(0.0074)
// "GBP" -> convertedIncome = income.times(0.0063)
// "CAD" -> convertedIncome = income.times(0.0099)
// "AUD" -> convertedIncome = income.times(0.0111)
// else -> convertedIncome = income.times(1.0)
// }
// }
// else{
// convertedIncome = income.times(1.0)
// }
// }
// }