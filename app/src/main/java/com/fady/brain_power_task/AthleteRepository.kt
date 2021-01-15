package com.fady.brain_power_task

import androidx.lifecycle.LiveData
import com.fady.brain_power_task.model.Athlete
import okhttp3.Dispatcher

class AthleteRepository(private var remoteSourceImp: RemoteSourceImp) {

    suspend fun getAthletesRemoteSource(
        onSuccess: (List<Athlete>) -> Unit, onFailed: (String) -> Unit
    ) {
        remoteSourceImp.getAthletes(onSuccess,onFailed)
    }
}