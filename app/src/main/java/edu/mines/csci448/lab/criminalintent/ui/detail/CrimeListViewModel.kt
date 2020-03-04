package edu.mines.csci448.lab.criminalintent.ui.detail

import androidx.lifecycle.ViewModel
import edu.mines.csci448.lab.criminalintent.data.CrimeRepository

class CrimeListViewModel(private val crimeRepository: CrimeRepository) : ViewModel() {
    val crimeListLiveData = crimeRepository.getCrimes()
}