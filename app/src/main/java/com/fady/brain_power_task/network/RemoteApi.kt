package com.fady.brain_power_task.network

import com.fady.brain_power_task.model.Athlete
import com.fady.brain_power_task.model.AthleteResponse
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApi {
    @GET("athletes.josn/")
    suspend fun getAllAthletes(): Response<AthleteResponse>
}