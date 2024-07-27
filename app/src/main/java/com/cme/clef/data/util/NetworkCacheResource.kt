package com.cme.clef.data.util

import com.cme.clef.data.remote.util.ApiEmptyResponse
import com.cme.clef.data.remote.util.ApiErrorResponse
import com.cme.clef.data.remote.util.ApiResponse
import com.cme.clef.data.remote.util.ApiSuccessResponse
import kotlinx.coroutines.flow.*


abstract class NetworkCacheResource<DB, REMOTE>() {

    private val TAG = "NetworkCacheResource"

    fun getResult() = flow<Resource<DB>> {

        emit(Resource.loading(null))
        val localData = fetchFromLocal()
        emit(Resource.loading(data = localData))

        if (shouldFetchFromRemote(localData)) {
            emit(Resource.loading(null))
            fetchFromRemote().collect { apiResponse ->
                when (apiResponse) {
                    is ApiSuccessResponse -> {
                           if (processRemoteResponse(apiResponse))   {
                               apiResponse.body?.let { saveRemoteData(it) }
                               emit(fetchFromLocal().let { dbData ->
                                   Resource.success(dbData)
                               })
                           } else {
                               emit(Resource.success(null))
                           }
                    }
                    is ApiErrorResponse -> {
                        emit(
                            Resource.error(
                                apiResponse.errorMessage,
                                null
                            )
                        )
                    }
                    is ApiEmptyResponse -> {
                        emit(Resource.success(null))
                    }

                    else -> {}
                }
            }
        } else {
            emit(fetchFromLocal().let { Resource.success(it) })
        }
    }

    protected abstract suspend fun fetchFromLocal(): DB?
    protected abstract suspend fun fetchFromRemote(): Flow<ApiResponse<REMOTE>>
    protected abstract suspend fun processRemoteResponse(
        response: ApiSuccessResponse<REMOTE>
    ): Boolean


    protected abstract suspend fun shouldFetchFromRemote(
        local: DB?,
    ): Boolean

    protected abstract suspend fun saveRemoteData(remote: REMOTE): Unit

}