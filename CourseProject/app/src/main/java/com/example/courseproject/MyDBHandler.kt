package com.example.courseproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase){
        val CREATE_TRANSACTIONS_TABLE = ("CREATE TABLE " + TABLE_TRANSACTIONS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TRANSACTIONNAME + " TEXT," + COLUMN_TRANSACTIONPRICE + " INTEGER," + COLUMN_TRANSACTIONCATEGORY + " TEXT," + COLUMN_TRANSACTIONDATE + " TEXT" + ")")
        db.execSQL(CREATE_TRANSACTIONS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS)
        onCreate(db)
    }

    fun addTransaction(transaction: Transaction){
        val values = ContentValues()
        values.put(COLUMN_TRANSACTIONNAME, transaction.transactionName)
        values.put(COLUMN_TRANSACTIONPRICE, transaction.transactionPrice)
        values.put(COLUMN_TRANSACTIONTOTAL, transaction.transactionTotal)
        values.put(COLUMN_TRANSACTIONCATEGORY, transaction.transactionCategory)
        values.put(COLUMN_TRANSACTIONDATE, transaction.transactionDate)

        val db = this.writableDatabase

        db.insert(TABLE_TRANSACTIONS, null, values)
        db.close()
    }

    fun findTransaction(transactionName: String?): Transaction? {
        val query = "SELECT * FROM $TABLE_TRANSACTIONS WHERE $COLUMN_TRANSACTIONNAME = \"$transactionName\""

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var transaction: Transaction? = null

        if(cursor.moveToFirst()){
            cursor.moveToFirst()

            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val price = cursor.getString(2).toDouble()
            val total = cursor.getString(3).toDouble()
            val category = cursor.getString(4)
            val date = cursor.getString(5)

            transaction = Transaction(id, name, price, total, date)
            cursor.close()
        }

        db.close()
        return transaction
    }

    fun deleteTransaction(transactionName: String?): Boolean {
        var result = false
        val query = "SELECT * FROM $TABLE_TRANSACTIONS WHERE $COLUMN_TRANSACTIONNAME = \"$transactionName\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_TRANSACTIONS, COLUMN_ID + " = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }

        db.close()
        return result
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "transactionDB.db"
        val TABLE_TRANSACTIONS = "transactions"
        val COLUMN_ID = "_id"
        val COLUMN_TRANSACTIONNAME = "transactionname"
        val COLUMN_TRANSACTIONPRICE = "transactionprice"
        val COLUMN_TRANSACTIONTOTAL = "transactiontotal"
        val COLUMN_TRANSACTIONCATEGORY = "transactioncategory"
        val COLUMN_TRANSACTIONDATE = "transactiondate"
    }
}