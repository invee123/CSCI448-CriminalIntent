package edu.mines.csci448.lab.criminalintent

import android.app.Application
import android.util.Log
import edu.mines.csci448.lab.criminalintent.data.CrimeRepository

class CriminalIntentApplication: Application() {
    private val logTag = "448.CriminalIntentApp"

    override fun onCreate() {
        super.onCreate()
        Log.d(logTag, "onCreate() called.")
    }
}