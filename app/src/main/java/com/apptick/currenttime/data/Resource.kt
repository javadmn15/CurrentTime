package com.codeclan.teammember.data.core

import com.apptick.currenttime.data.Status

data class Resource<out T>(val status: Status, val data: T?, val errorData: ErrorData? = null, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)
        fun <T> error(data: T?=null,errorData:ErrorData? = null, message: String?=null): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message,errorData = errorData)
        fun <T> loading(data: T?=null): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
        fun <T> idle(): Resource<T> = Resource(status = Status.IDLE, data = null, message = null)
    }
}