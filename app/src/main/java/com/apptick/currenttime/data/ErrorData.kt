package com.codeclan.teammember.data.core

import javax.annotation.Nullable

@Nullable
data class ErrorData(
    val reason:ErrorType,
    val message:String
)
