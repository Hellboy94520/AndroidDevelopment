package com.example.hellboy.mysql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /* ----------------------------------------
    SQL FUNCTION TO MANAGE DATA
    ---------------------------------------- */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
    }


    /* ----------------------------------------
    Conversion Kotlin Data to SQL and add to Database
    ---------------------------------------- */
    @Throws(SQLiteConstraintException::class)
    fun insertUser(pUser: UserModel): Boolean {

        // Get the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(UserDBContract.UserEntry.COLUMN_USER_ID, pUser.userid)
        values.put(UserDBContract.UserEntry.COLUMN_NAME, pUser.name)
        values.put(UserDBContract.UserEntry.COLUMN_AGE, pUser.age)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(UserDBContract.UserEntry.TABLE_NAME, null, values)

        return true
    }


    /* ----------------------------------------
    Delete SQL Data with a UserId
    ---------------------------------------- */
    @Throws(SQLiteConstraintException::class)
    fun deleteUser(pUserid: String) : Boolean {

        // Get the data repository in write mode
        val db = writableDatabase

        // Define 'where' part of query
        val selection = UserDBContract.UserEntry.COLUMN_USER_ID + " LIKE ?"

        // Specify arguments in placeholder order
        val selectionArgs = arrayOf(pUserid)

        // Issue SQL statement
        db.delete(UserDBContract.UserEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }

    /* ----------------------------------------
    Find user(s) in database
    ---------------------------------------- */
    fun readUser(pUserid: String) : ArrayList<UserModel> {

        //Read the DataBase and create a Cursor to get the corresponding user
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + UserDBContract.UserEntry.TABLE_NAME + " WHERE " +
            UserDBContract.UserEntry.COLUMN_USER_ID + "='" + pUserid + "'", null)
        } catch (e:SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        //Conversion of each User found in Kotlin Class
        val users = ArrayList<UserModel>()
        if(cursor!!.moveToFirst()) {
            while(!cursor.isAfterLast) {
                users.add(
                    UserModel(pUserid,
                        cursor.getString(cursor.getColumnIndex(UserDBContract.UserEntry.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(UserDBContract.UserEntry.COLUMN_AGE))
                    )
                )
            }
        }

        return users
    }

    /* ----------------------------------------
    Return all users from Database
    ---------------------------------------- */
    fun readAllUsers() : ArrayList<UserModel> {


        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + UserDBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        val users = ArrayList<UserModel>()
        if(cursor!!.moveToFirst()) {
            while(!cursor.isAfterLast)
            {
                users.add(
                    UserModel(cursor.getString(cursor.getColumnIndex(UserDBContract.UserEntry.COLUMN_USER_ID)),
                        cursor.getString(cursor.getColumnIndex(UserDBContract.UserEntry.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(UserDBContract.UserEntry.COLUMN_AGE)))
                )
                cursor.moveToNext()
            }
        }

        return users
    }


    companion object {
        val DATABASE_VERSION=1
        val DATABASE_NAME= "FeedReader.db"

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserDBContract.UserEntry.TABLE_NAME + " (" +
                        UserDBContract.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                        UserDBContract.UserEntry.COLUMN_NAME + " TEXT," +
                        UserDBContract.UserEntry.COLUMN_AGE + " TEXT)"

        private val SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserDBContract.UserEntry.TABLE_NAME
    }
}