package com.example.courseproject

class UserBill(
    override var transactionName: String,
    override var transactionPrice: Double,
    override var transactionCategory: String,
    override var transactionDate: String,
) : Transaction