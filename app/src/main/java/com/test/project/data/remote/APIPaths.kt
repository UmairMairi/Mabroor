package com.test.project.data.remote

import com.test.project.data.model.BuyDataModel
import com.test.project.data.model.CallDataModel
import retrofit2.http.GET

interface APIPaths {

    @GET("call")
    suspend fun getCallList(
    ): ArrayList<CallDataModel>

    @GET("buy")
    suspend fun getBuyList(
    ): ArrayList<BuyDataModel>


}