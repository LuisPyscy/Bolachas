package com.example.bolachas

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.novo.CreateDB

class DatabaseController(context: Context) {

    private val database: CreateDB
    private val writableDatabase: SQLiteDatabase
    private val readableDatabase: SQLiteDatabase

    init {
        database = CreateDB(context)
        writableDatabase = database.writableDatabase
        readableDatabase = database.readableDatabase
    }

    fun insertData(artist: String, record: String, year: String, style: String, shelf: String): String {
        val values = ContentValues()
        values.put(CreateDB.ARTIST, artist)
        values.put(CreateDB.RECORD, record)
        values.put(CreateDB.YEAR, year)
        values.put(CreateDB.STYLE, style)
        values.put(CreateDB.SHELF, shelf)
        val result = writableDatabase.insert(CreateDB.TABLE_NAME, null, values)
        return if (result == -1L) "Error inserting record" else "Discos Salvos"
    }

    fun loadData(): Cursor? {
        val fields = arrayOf(CreateDB.ID, CreateDB.ARTIST, CreateDB.RECORD, CreateDB.SHELF)
        val cursor = readableDatabase.query(CreateDB.TABLE_NAME, fields, null, null,
            null, null, null, null)
        cursor.moveToFirst()
        return cursor
    }

    fun loadDataById(id: Int): Cursor? {
        val fields = arrayOf(CreateDB.ID, CreateDB.ARTIST, CreateDB.RECORD, CreateDB.YEAR, CreateDB.STYLE, CreateDB.SHELF)
        val where = "${CreateDB.ID} = ?"
        val whereArgs = arrayOf(id.toString())
        val cursor = readableDatabase.query(CreateDB.TABLE_NAME, fields, where, whereArgs, null,
            null, null, null)
        cursor.moveToFirst()
        return cursor
    }

    fun updateData(id: Int, artist: String, record: String, year: String, style: String, shelf: String) {
        val values = ContentValues()
        values.put(CreateDB.ARTIST, artist)
        values.put(CreateDB.RECORD, record)
        values.put(CreateDB.YEAR, year)
        values.put(CreateDB.STYLE, style)
        values.put(CreateDB.SHELF, shelf)
        val where = "${CreateDB.ID} = ?"
        val whereArgs = arrayOf(id.toString())
        writableDatabase.update(CreateDB.TABLE_NAME, values, where, whereArgs)
    }

    fun deleteData(id: Int) {
        val where = "${CreateDB.ID} = ?"
        val whereArgs = arrayOf(id.toString())
        writableDatabase.delete(CreateDB.TABLE_NAME, where, whereArgs)
    }
}