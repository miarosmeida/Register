package com.example.uas201804018

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperOrder(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "Product.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBInfoOrder.UserInput.TABLE_NAME + " (" + DBInfoOrder.UserInput.COL_IDP +
                    " VARCHAR(200) PRIMARY KEY, " + DBInfoOrder.UserInput.COL_NAMA + " TEXT, " +
                    DBInfoOrder.UserInput.COL_HARGA + " VARCHAR(200), " + DBInfoOrder.UserInput.COL_WARNA +
                    " VARCHAR(30) )"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfoOrder.UserInput.TABLE_NAME
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertData(idpin: String, namain: String, hargain: String, warnain: String): Boolean {
        val db = writableDatabase
        val namatablet = DBInfoOrder.UserInput.TABLE_NAME
        val idpit = DBInfoOrder.UserInput.COL_IDP
        val namat = DBInfoOrder.UserInput.COL_NAMA
        val hargat = DBInfoOrder.UserInput.COL_HARGA
        val warnat = DBInfoOrder.UserInput.COL_WARNA

        var sql = "INSERT INTO "+ namatablet +"("+idpit+", "+namat+", "+hargat+", "+warnat+") " +
                "VALUES('"+idpin+"', '"+namain+"', '"+hargain+"', '"+warnain+"')"
        db.execSQL(sql)
        return true
    }
    fun fullData():ArrayList<DBModelOrder>{
        val order = arrayListOf<DBModelOrder>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+ DBInfoOrder.UserInput.TABLE_NAME, null)
        }catch (e: SQLException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var idpt: String
        var namat: String
        var hargat: String
        var warnat: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                idpt = cursor.getString(cursor.getColumnIndex(DBInfoOrder.UserInput.COL_IDP))
                namat = cursor.getString(cursor.getColumnIndex(DBInfoOrder.UserInput.COL_NAMA))
                hargat = cursor.getString(cursor.getColumnIndex(DBInfoOrder.UserInput.COL_HARGA))
                warnat = cursor.getString(cursor.getColumnIndex(DBInfoOrder.UserInput.COL_WARNA))
                order.add(DBModelOrder(idpt, namat, hargat, warnat))
                cursor.moveToNext()
            }
        }
        return  order
    }
    fun deleteData(idpin: String){
        val db = writableDatabase
        val namatablet = DBInfoOrder.UserInput.TABLE_NAME
        val idpt = DBInfoOrder.UserInput.COL_IDP
        val sql = "DELETE FROM " +namatablet+ " WHERE " +idpt+"='"+idpin+"'"
        db.execSQL(sql)
    }
    fun updateData(idpin: String, namain: String, hargain: String, warnain: String) {
        val db = writableDatabase
        val namatablet = DBInfoOrder.UserInput.TABLE_NAME
        val idpt = DBInfoOrder.UserInput.COL_IDP
        val namat = DBInfoOrder.UserInput.COL_NAMA
        val hargat = DBInfoOrder.UserInput.COL_HARGA
        val warnat = DBInfoOrder.UserInput.COL_WARNA
        var sql = "UPDATE "+ namatablet + " SET "+
                namat+"='"+namain+"', "+hargat+"='"+hargain+"', "+warnat+"='"+warnain+"' "+
                "WHERE "+idpt+"='"+idpin+"'"
        db.execSQL(sql)

    }

}