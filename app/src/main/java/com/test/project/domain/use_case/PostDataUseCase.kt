package com.test.project.domain.use_case

import com.test.project.common.Resource
import com.test.project.data.model.CallDataModel
import com.test.project.data.model.DataModel
import com.test.project.domain.repository.CommonRepository
import com.test.project.domain.repository.CommonRepositoryTwo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostDataUseCase @Inject constructor(private val repository: CommonRepositoryTwo) {

    operator fun invoke(): Flow<Resource<DataModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getPosts()
            emit(Resource.Success(data = data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }


}