package edu.mines.csci448.lab.criminalintent.ui.detail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.mines.csci448.lab.criminalintent.R
import edu.mines.csci448.lab.criminalintent.data.Crime

class CrimeHolderr(val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var crime: Crime
    private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
    private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
    private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)


    fun bind(crime: Crime) {
        this.crime = crime
        titleTextView.text = this.crime.title
        dateTextView.text = this.crime.date.toString()
        solvedImageView.visibility = if(this.crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}