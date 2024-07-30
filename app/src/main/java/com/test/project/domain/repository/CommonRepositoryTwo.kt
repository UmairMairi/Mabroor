package com.test.project.domain.repository

import com.test.project.data.model.BuyDataModel
import com.test.project.data.model.CallDataModel
import com.test.project.data.model.DataModel

interface CommonRepositoryTwo {

    suspend fun getPosts(): DataModel



}