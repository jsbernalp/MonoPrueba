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
        private val DATABASE_VERSION = 6
        private val DATABASE_NAME = "Monolegal.db"
        //TABLE
        private val TABLE_NAME = "Ciudad"

        //COLUMNS CIUDAD
        private val COL_ID = "Id"
        private val COL_NAME = "Name"
        private val COL_CODE = "Code"
        private val COL_STATE = "State"

        var id_user: Int = 0
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String =
            ("CREATE TABLE $TABLE_NAME ($COL_ID TEXT, $COL_CODE TEXT PRIMARY KEY, $COL_NAME TEXT, $COL_STATE TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)


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