package com.fady.brain_power_task


import android.util.Log
import androidx.lifecycle.*
import com.fady.brain_power_task.model.Athlete
import com.fady.brain_power_task.model.AthleteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
class MainActivityViewModel(
    private val repository: AthleteRepository
) :
    ViewModel() {
    private val showLoadingLiveData = MutableLiveData<Boolean>()
    private val showErrorLiveData = MutableLiveData<String>()
    private val athletesLiveData = MutableLiveData<List<Athlete>>()

    fun getAthletes() {
        showLoadingLiveData.value = true
        viewModelScope.launch {
            repository.getAthletesRemoteSource({
                athletesLiveData.postValue(it)
                showLoadingLiveData.postValue(false)
            }, {
                showErrorLiveData.postValue(it)
                showLoadingLiveData.postValue(false)
            })
        }
    }
    fun getShowLoadingLiveData(): LiveData<Boolean> = showLoadingLiveData
    fun getShowErrorLiveData(): LiveData<String> = showErrorLiveData
    fun getAthletesLiveData(): LiveData<List<Athlete>> = athletesLiveData
}