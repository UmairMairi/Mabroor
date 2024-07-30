package com.test.project.data.repository

import com.test.project.data.model.BuyDataModel
import com.test.project.data.model.CallDataModel
import com.test.project.data.model.DataModel
import com.test.project.data.remote.APIPaths
import com.test.project.data.remote.APIPathsTwo
import com.test.project.domain.repository.CommonRepository
import com.test.project.domain.repository.CommonRepositoryTwo

class CommonRepositoryImplTwo(private val apiPath: APIPathsTwo) : CommonRepositoryTwo {
    override suspend fun getPosts(): DataModel {
        return apiPath.getPosts()
    }


}