package com.jsb.monoprueba.data.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import android.database.sqlite.SQLiteOpenHelper
import com.jsb.monoprueba.model.Ciudad
import com.jsb.monoprueba.model.Usuario
import javax.inject.Inject

class AppDatabase(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 8
        private val DATABASE_NAME = "Monolegal.db"
        //TABLE
        private val TABLE_NAME = "Ciudad"
        private val TABLE_USER = "Usuario"

        //COLUMNS USUARIO
        private val COL_NAMES = "Nombres"
        private val COL_EMAIL = "Email"
        private val COL_PASSWORD = "Password"

        //COLUMNS CIUDAD
        private val COL_ID = "Id"
        private val COL_NAME = "Name"
        private val COL_CODE = "Code"
        private val COL_STATE = "State"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String =
            ("CREATE TABLE $TABLE_NAME ($COL_ID TEXT, $COL_CODE TEXT PRIMARY KEY, $COL_NAME TEXT, $COL_STATE TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)

        val CREATE_TABLE_USER: String =
            ("CREATE TABLE $TABLE_USER ($COL_NAMES TEXT, $COL_EMAIL TEXT PRIMARY KEY, $COL_PASSWORD TEXT)")
        db!!.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)

    }



    //CRUD USER

    fun addUser(usuario: Usuario){
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAMES,usuario.Nombres)
        values.put(COL_EMAIL,usuario.Email)
        values.put(COL_PASSWORD,usuario.Password)
        db.insert(TABLE_USER,null,values)
        db.close()
    }

    fun getUsers(): ArrayList<Usuario>{
        var lstUsuario= ArrayList<Usuario>()
        val selectUser = "SELECT * FROM $TABLE_USER"
        val db1:SQLiteDatabase = this.writableDatabase
        val cursor1: Cursor = db1.rawQuery(selectUser,null)
        if (cursor1.moveToFirst()){
            do{
                var Nombres:String = cursor1.getString(cursor1.getColumnIndex(COL_NAMES))
                var Email:String = cursor1.getString(cursor1.getColumnIndex(COL_EMAIL))
                var Password:String = cursor1.getString(cursor1.getColumnIndex(COL_PASSWORD))
                var usuario = Usuario()
                usuario.Nombres = Nombres
                usuario.Email = Email
                usuario.Password = Password
                lstUsuario.add(usuario)
            }while (cursor1.moveToNext())
        }
        db1.close()
        return lstUsuario
    }

    //CRUD CIUDADES
    val allCities:ArrayList<Ciudad>
        get() {
            val lstCities= ArrayList<Ciudad>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db:SQLiteDatabase = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery,null)
            if (cursor.moveToFirst()){
                do {
                    var Id:String = cursor.getString(cursor.getColumnIndex(COL_ID))
                    var Name:String = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    var Code:String = cursor.getString(cursor.getColumnIndex(COL_CODE))
                    var State:String = cursor.getString(cursor.getColumnIndex(COL_STATE))
                    val ciudad = Ciudad(Id, Code, Name, State)
                    lstCities.add(ciudad)
                }while (cursor.moveToNext())
            }
            db.close()
            return lstCities
        }


    fun addCity(ciudad: ArrayList<Ciudad>,estado:String){
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        for (c: Ciudad in ciudad){
            values.put(COL_NAME,c.Nombre)
            if (c.Nombre.equals("Medellin")){
                values.put(COL_CODE,"105001")
            }else {
                values.put(COL_CODE, c.Code)
            }
            values.put(COL_ID,c.Id)
            values.put(COL_STATE, estado)
            db.insert(TABLE_NAME,null,values)
        }
        db.close()
    }

    fun updateSql(code:String){
        if (code.equals("05001")){
            val db:SQLiteDatabase = this.writableDatabase
            var cv = ContentValues()
            cv.put(COL_STATE,"Activo")
            db.update(TABLE_NAME,cv, "$COL_CODE= 105001",null)
            db.close()
        }else{
            val db:SQLiteDatabase = this.writableDatabase
            var cv = ContentValues()
            cv.put(COL_STATE,"Activo")
            db.update(TABLE_NAME,cv, "$COL_CODE= $code",null)
            db.close()
        }
    }
}