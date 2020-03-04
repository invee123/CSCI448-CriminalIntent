package edu.mines.csci448.lab.criminalintent.data

import androidx.room.PrimaryKey
import java.util.*

data class Crime(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: Date = Date(),
                 var isSolved: Boolean = false)