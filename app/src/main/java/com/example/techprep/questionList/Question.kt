package com.example.techprep.questionList

import android.support.annotation.Keep
import com.example.techprep.database.QuestionJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Question(
    @SerialName("api_id") val api_id: String?,
    @SerialName("question") val question: String?,
    @SerialName("description") val description: String?,
    @SerialName("answers") val answers: List<QuestionJson>?,
    @SerialName("multiple_correct_answers") val multiple_correct_answers: String?,
    @SerialName("correct_answers") val correct_answers:  List<QuestionJson>?,
    @SerialName("explanation") val explanation: String?,
    @SerialName("tip") val tip: String?,
    @SerialName("tags") val tags: String?,
    @SerialName("category") val category: String?,
    @SerialName("difficulty") val difficulty: String?
) : java.io.Serializable