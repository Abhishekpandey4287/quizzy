package com.example.quizzy.network

import com.example.quizzy.data.model.GeneralResponse
import com.example.quizzy.data.model.StudentDashboard
import retrofit2.http.Body
import retrofit2.http.GET

interface EndPoint {
    @GET("v0/b/user-contacts-ade83.appspot.com/o/student_dashboard.json?alt=media&token=0091b4c2-2ee2-4326-99cd-96d5312b34bd")
    suspend fun getStudentDashboard(): StudentDashboard
}