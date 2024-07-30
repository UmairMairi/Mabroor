package com.test.project.domain.use_case

import com.test.project.common.Resource
import com.test.project.data.model.CallDataModel
import com.test.project.domain.repository.CommonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCallListUseCase @Inject constructor(private val repository: CommonRepository) {

    operator fun invoke(): Flow<Resource<List<CallDataModel>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getCallLIst()
            emit(Resource.Success(data = data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }


}