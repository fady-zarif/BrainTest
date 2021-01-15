package com.fady.brain_power_task.di

import com.fady.brain_power_task.AthleteRepository
import com.fady.brain_power_task.MainActivityViewModel
import com.fady.brain_power_task.RemoteSourceImp
import com.fady.brain_power_task.network.RemoteApi
import com.fady.brain_power_task.network.getRetrofitClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object AppModule {

    val networkModule = module {
        val baseUrl =
            "https://gist.githubusercontent.com/Bassem-Samy/f227855df4d197d3737c304e9377c4d4/raw/ece2a30b16a77ee58091886bf6d3445946e10a23/"

        fun getRetrofit(retrofit: Retrofit) =
            retrofit.create(RemoteApi::class.java)

        val remoteApi = getRetrofit(getRetrofitClient(baseUrl))

        single { remoteApi }
    }
    val viewModule = module {
        single { RemoteSourceImp(get()) }
        single { AthleteRepository(get()) }
        viewModel { MainActivityViewModel(get()) }
    }
}