package com.example.quizzy.data.model

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val status: Boolean,
    val message: String
)
