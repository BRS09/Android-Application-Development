package com.example.courseproject

class Transaction {
    var id: Int = 0
    var transactionName: String? = null
    var transactionPrice: Double? = null
    var transactionTotal: Double? = null
    var transactionCategory: String? = null
    var transactionDate: String? = null

    constructor(id: Int, transactionName: String, transactionPrice: Double, transactionTotal: Double, transactionDate: String){
        this.id = id
        this.transactionName = transactionName
        this.transactionPrice = transactionPrice
        this.transactionCategory = transactionCategory
        this.transactionDate = transactionDate
    }

    constructor(transactionName: String, transactionPrice: Double, transactionTotal: Double, transactionDate: String){
        this.transactionName = transactionName
        this.transactionPrice = transactionPrice
        this.transactionCategory = transactionCategory
        this.transactionDate = transactionDate
    }
}