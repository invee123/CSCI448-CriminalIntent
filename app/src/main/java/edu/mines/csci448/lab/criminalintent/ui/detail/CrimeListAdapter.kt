package edu.mines.csci448.lab.criminalintent.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.mines.csci448.lab.criminalintent.R
import edu.mines.csci448.lab.criminalintent.data.Crime

class CrimeListAdapter(private val crimes: List<Crime>, private val clickListener: (Crime) -> Unit) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_crime, parent, false)
        return CrimeHolder(view)
    }

    override fun getItemCount() = crimes.size

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime, clickListener)
    }
}