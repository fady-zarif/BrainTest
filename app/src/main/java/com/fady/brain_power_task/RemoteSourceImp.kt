package com.fady.brain_power_task

import android.util.Log
import com.fady.brain_power_task.model.Athlete
import com.fady.brain_power_task.network.RemoteApi

class RemoteSourceImp(private val remoteApi: RemoteApi) {
    suspend fun getAthletes(
        onSuccess: (List<Athlete>) -> Unit,
        onFailed: ((String) -> Unit)
    ) {
        val response = remoteApi.getAllAthletes()
        if (response.isSuccessful) {
            response.body()?.athletes?.let { onSuccess(it) }
        } else
            onFailed(response.message())
    }
}