package com.test.project.domain.repository

import com.test.project.data.model.BuyDataModel
import com.test.project.data.model.CallDataModel

interface CommonRepository {

    suspend fun getBuyList():ArrayList<BuyDataModel>
    suspend fun getCallLIst(): ArrayList<CallDataModel>


}