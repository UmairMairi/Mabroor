package com.test.project.data.remote

import com.test.project.data.model.BuyDataModel
import com.test.project.data.model.CallDataModel
import com.test.project.data.model.DataModel
import retrofit2.http.GET
import retrofit2.http.POST

interface APIPathsTwo {

    @POST("posts")
    suspend fun getPosts(
    ): DataModel



}