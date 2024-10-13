package com.apptick.currenttime.data

import com.codeclan.teammember.data.core.Resource
import com.codeclan.teammember.data.sources.BaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: NetworkService
) : BaseDataSource(){
    fun getCurrentTime(): Flow<Resource<TimeModel>> {
        return flow{
            emit(Resource.loading())
            emit(getResult { service.getCurrentTime() })
        }.catch { error->
            emit(Resource.error(message = error.message))
        }.flowOn(Dispatchers.IO)
    }
}