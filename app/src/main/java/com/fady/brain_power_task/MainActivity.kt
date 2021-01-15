package com.fady.brain_power_task

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.fady.brain_power_task.adapter.AthletesAdapter
import com.fady.brain_power_task.model.Athlete
import com.fady.brain_power_task.model.AthleteResponse
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

const val GRID_SPAN_COUNT = 2

class MainActivity : AppCompatActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    private lateinit var rvAdapter: AthletesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

        mainActivityViewModel.getAthletesLiveData()
            .observe(this, Observer { athletesObserver(it) })
        mainActivityViewModel.getShowErrorLiveData()
            .observe(this, Observer { errorMessageObserver(it) })
        mainActivityViewModel.getShowLoadingLiveData()
            .observe(this, Observer { loadingObserver(it) })

        mainActivityViewModel.getAthletes()
    }

    private fun initRecycler() {
        rvAthletes.layoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)
        rvAdapter = AthletesAdapter { athlete, _ ->
            val bottomSheetView = BottomSheetView(athlete)
            bottomSheetView.show(supportFragmentManager, null)
        }
        rvAthletes.adapter = rvAdapter
    }

    private fun athletesObserver(athletesList: List<Athlete>) {
        rvAdapter.submitList(athletesList)
    }

    private fun errorMessageObserver(errorMessage: String) { // TODO: 6/16/20 change that later 
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun loadingObserver(showLoading: Boolean) {
        pbloading.visibility = if (showLoading) View.VISIBLE else View.GONE
        rvAthletes.visibility = if (showLoading) View.GONE else View.VISIBLE
    }

}