package edu.mines.csci448.lab.criminalintent.ui.detail

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.mines.csci448.lab.criminalintent.R
import edu.mines.csci448.lab.criminalintent.data.Crime
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.list_item_crime.*
import java.util.*

private const val ARG_CRIME_ID = "crime_id"
class CrimeDetailFragment : Fragment() {

    private val logTag = "448.CrimeDetailFrag"
    private lateinit var crime: Crime
    private lateinit var crimeDetailViewModel: CrimeDetailViewModel
    private lateinit var titleTextField: EditText
    private lateinit var dateButton: Button
    private lateinit var isSolved: CheckBox



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(logTag, "onCreate() called")
        crime = Crime()
        val factory = CrimeDetailViewModelFactory(requireContext())
        crimeDetailViewModel = ViewModelProvider(this, factory)
            .get(CrimeDetailViewModel::class.java)
        val crimeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        crimeDetailViewModel.loadCrime(crimeId)
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, "onStart() called")

        val titleWatcher: TextWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //does nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                //does nothing
            }
        }

        titleTextField.addTextChangedListener(titleWatcher)

        isSolved.apply {
            setOnCheckedChangeListener{_, isChecked -> crime.isSolved = isChecked}
        }
    }

    companion object {
        fun newInstance(crimeId: UUID): CrimeDetailFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, crimeId)
            }
            return CrimeDetailFragment().apply {
                arguments = args
            }
        }
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
        crimeDetailViewModel.saveCrime(crime)
    }

    override fun onDestroy() {
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {
        Log.d(logTag, "onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        titleTextField = view.findViewById(R.id.crime_title_edit_text) as EditText
        dateButton = view.findViewById(R.id.crime_date_button) as Button
        isSolved = view.findViewById(R.id.crime_solved_checkbox) as CheckBox

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d(logTag, "onViewCreated() called")
        super.onViewCreated(view, savedInstanceState)
        crimeDetailViewModel.crimeLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { crime ->
                crime?.let {
                    this.crime = crime
                    updateUI()
                }
            })
    }
    override fun onActivityCreated(savedInstanceState: Bundle?){
        Log.d(logTag, "onActivityCreated() called")
        super.onActivityCreated(savedInstanceState)
    }
    override fun onDestroyView(){
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()
    }
    override fun onDetach(){
        Log.d(logTag, "onDetach() called")
        super.onDetach()
    }

    private fun updateUI() {
        titleTextField.setText(crime.title)
        dateButton.text = crime.date.toString()
        isSolved.apply {
            isChecked = crime.isSolved
            jumpDrawablesToCurrentState()
        }
    }
}