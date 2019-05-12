package com.mpowloka.data.networking

import com.mpowloka.data.networking.model.JsonDataModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PvxApi {

    @GET("/test.json")
    fun downloadData(): Observable<JsonDataModel>

    companion object {

        fun newInstance(): PvxApi {
            return Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PvxApi::class.java)
        }

        private const val API_ENDPOINT = "http://tmp.peoplevox.net"

    }

}