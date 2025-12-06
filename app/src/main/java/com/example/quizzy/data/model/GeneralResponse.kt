package com.example.quizzy.data.model

import androidx.annotation.Keep

@Keep
data class GeneralResponse<Type>(
    var status: Boolean = false,
    var code: Int? = 200,
    var message: String = "",
    var data: Type? = null
)
