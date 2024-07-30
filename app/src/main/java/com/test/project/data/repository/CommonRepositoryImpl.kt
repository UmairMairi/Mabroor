package com.test.project.data.repository

import com.test.project.data.model.BuyDataModel
import com.test.project.data.model.CallDataModel
import com.test.project.data.remote.APIPaths
import com.test.project.domain.repository.CommonRepository

class CommonRepositoryImpl(private val apiPath: APIPaths) : CommonRepository {
    override suspend fun getBuyList(): ArrayList<BuyDataModel> {
        return apiPath.getBuyList()
    }

    override suspend fun getCallLIst(): ArrayList<CallDataModel> {
        return apiPath.getCallList()
    }

}