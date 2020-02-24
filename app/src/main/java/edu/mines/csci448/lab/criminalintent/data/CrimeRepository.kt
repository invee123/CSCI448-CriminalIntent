package edu.mines.csci448.lab.criminalintent.data

import android.content.Context
import androidx.room.Room

private const val DATABASE_NAME = "crime-database"

class CrimeRepository(private val crimeDao: CrimeDao) {

    companion object {
        private var instance: CrimeRepository? = null
        fun getInstance(context: Context): CrimeRepository? {
            return instance ?: let {
                if (instance == null) {
                    val database = CrimeDatabase.getInstance(context)
                    instance = CrimeRepository(database.crimeDao())
                }
                return instance
            }
        }
    }
}