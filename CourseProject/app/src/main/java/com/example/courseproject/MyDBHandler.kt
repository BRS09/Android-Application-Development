package com.example.courseproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHandler(_context: Context) {
    private val DATABASE_NAME: String = "Database"
    private var mContext: Context? = null
    private var mDbHelper: MyDBHelper? = null
    private var mSQLiteDatabase: SQLiteDatabase? = null
    private val DATABASE_VERSION = 1

    init {
        this.mContext = _context
        mDbHelper = MyDBHelper(_context, DATABASE_NAME, null, DATABASE_VERSION)
    }

    fun open(){
        mSQLiteDatabase = mDbHelper?.writableDatabase
    }

    inner class MyDBHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version){
        override fun onCreate(_db: SQLiteDatabase?){
            val query = "CREATE TABLE transactions(id integer primary key autoincrement, transactionname text, transactionprice integer, transactioncategory text, transactiondate text);"
            val queryTwo = "CREATE TABLE bills(id integer primary key autoincrement, billname text, billprice integer, billcategory text, billdate text);"
            _db?.execSQL(query)
            _db?.execSQL(queryTwo)
        }

        override fun onUpgrade(_db: SQLiteDatabase?, _oldVersion: Int, _newVersion: Int){
            val query = "DROP TABLE IF EXISTS transactions;"
            val queryTwo = "DROP TABLE IF EXISTS bills;"
            _db?.execSQL(query)
            _db?.execSQL(queryTwo)
            onCreate(_db)
        }
    }

    fun insertTransaction(transactionName: String, transactionPrice: Double, transactionCategory: String, transactionDate: String){
        val cv: ContentValues = ContentValues()
        cv.put("transactionname", transactionName)
        cv.put("transactionprice", transactionPrice)
        cv.put("transactioncategory", transactionCategory)
        cv.put("transactiondate", transactionDate)

        mSQLiteDatabase?.insert("transactions", null, cv)
    }

    fun selectAllTransactions(): List<String> {
        var allTransactions: MutableList<String> = ArrayList()
        var cursor: Cursor = mSQLiteDatabase?.rawQuery("SELECT * FROM transactions", null)!!
        if(cursor.moveToFirst()){
            do {
                allTransactions.add(cursor.getString(1).toString())
                allTransactions.add(cursor.getString(2).toString())
                allTransactions.add(cursor.getString(3).toString())
                allTransactions.add(cursor.getString(4).toString())
            } while(cursor.moveToNext())
        }

        return allTransactions
    }

    fun deleteAllTransactions(){
        mSQLiteDatabase?.delete("transactions", null, null)
    }

    fun insertBill(billName: String, billPrice: Double, billCategory: String, billDate: String){
        val cv: ContentValues = ContentValues()
        cv.put("billname", billName)
        cv.put("billprice", billPrice)
        cv.put("billcategory", billCategory)
        cv.put("billdate", billDate)

        mSQLiteDatabase?.insert("bills", null, cv)
    }

    fun selectAllBills(): List<String> {
        var allBills: MutableList<String> = ArrayList()
        var cursor: Cursor = mSQLiteDatabase?.rawQuery("SELECT * FROM bills", null)!!

        if(cursor.moveToFirst()){
            do {
                allBills.add(cursor.getString(1).toString())
                allBills.add(cursor.getString(2).toString())
                allBills.add(cursor.getString(3).toString())
                allBills.add(cursor.getString(4).toString())
            } while(cursor.moveToNext())
        }
        return allBills
    }
}