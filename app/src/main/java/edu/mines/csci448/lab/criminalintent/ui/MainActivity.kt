package edu.mines.csci448.lab.criminalintent.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.mines.csci448.lab.criminalintent.R
import edu.mines.csci448.lab.criminalintent.ui.detail.CrimeDetailFragment
import edu.mines.csci448.lab.criminalintent.ui.detail.CrimeListFragment
import edu.mines.csci448.lab.criminalintent.ui.detail.CrimeListViewModel
import edu.mines.csci448.lab.criminalintent.ui.detail.CrimeListViewModelFactory
import edu.mines.csci448.lab.criminalintent.ui.pager.CrimePagerFragment
import java.util.*

private const val logTag = "448.MainActivity"
class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {

    private var detailContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")

        setContentView(R.layout.activity_masterdetail)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = CrimeListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
                .commit()
        }

        detailContainer = findViewById(R.id.detail_fragment_container)
    }

    override fun onCrimeSelected(crimeId: UUID) {
        if(detailContainer == null) {
            val fragment = CrimePagerFragment.newInstance(crimeId)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            val fragment = CrimePagerFragment.newInstance(crimeId)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detail_fragment_container, fragment)
                .commit()
        }
    }

    override fun onStart() {
        //TODO this is wrong?
        super.onStart()
        Log.d(logTag, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "onResume() called")
    }

    override fun onPause() {
        Log.d(logTag, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(logTag, "onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }
}

