package com.example.lib.net
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofit : Retrofit.Builder =Retrofit.Builder()
        .baseUrl("https://baobab.kaiyanapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

    fun <T> getService(service :Class<T>):T{
        return retrofit.build().create(service)
    }
}