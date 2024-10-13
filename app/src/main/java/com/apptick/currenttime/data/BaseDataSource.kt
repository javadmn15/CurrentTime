package com.codeclan.teammember.data.sources


import android.util.Log
import com.codeclan.teammember.data.core.ErrorData
import com.codeclan.teammember.data.core.ErrorType
import com.codeclan.teammember.data.core.Resource
import org.json.JSONObject
import retrofit2.Response


abstract class BaseDataSource {
    private val TAG: String = "getResult"

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        Resource.loading(null)
        try {

            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
                Log.d(TAG, "getResult: success ${response.body()} }}" )
            }
            val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())

            Log.d(TAG, "getResult:try ${jsonObj.getString("message")} }}" )
            return Resource.error(errorData = ErrorData(ErrorType.Server,jsonObj.getString("message")?:""),message = jsonObj.getString("message"))
        } catch (e: Exception) {
            Log.d(TAG, "getResult: catch ${e.message} }}" )
            return Resource.error(errorData = ErrorData(ErrorType.Server,e.message?:""),message = e.message)
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.d(TAG, "getResult: error $message }}" )
        return Resource.error(errorData = ErrorData(ErrorType.Server, message),message=message)
    }

}