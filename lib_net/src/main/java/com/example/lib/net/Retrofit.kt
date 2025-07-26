package com.example.lib.net
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
// 创建一个名为Retrofit的单例
object Retrofit {
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://baobab.kaiyanapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun <T> getService(service :Class<T>):T{
        return retrofit.create(service)
    }
}