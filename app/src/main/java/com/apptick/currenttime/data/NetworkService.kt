package com.apptick.currenttime.data

import retrofit2.Response
import retrofit2.http.*

interface NetworkService {
    @GET("time-date/?json=fa")
    suspend fun getCurrentTime(): Response<TimeModel>



}