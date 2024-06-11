package com.example.novo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class CreateDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Bolacha"
        internal const val TABLE_NAME = "music"
        internal const val ID = "id"
        internal const val ARTIST = "artist"
        internal const val RECORD = "label"
        internal const val YEAR = "year"
        internal const val STYLE = "style"
        internal const val SHELF = "shelf"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE IF NOT EXISTS $TABLE_NAME ("
                + "$ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$ARTIST TEXT,"
                + "$RECORD TEXT,"
                + "$YEAR TEXT,"
                + "$STYLE TEXT,"
                + "$SHELF TEXT)"
                )
        db.execSQL(createTable)
        // Exibe o SQL de criação do banco de dados no log
        Log.d("CreateDB", "SQL de criação do banco de dados: $createTable")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


}

