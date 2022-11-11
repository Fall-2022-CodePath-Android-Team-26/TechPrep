package com.example.techprep.questionList

import android.support.annotation.Keep
import com.example.techprep.database.QuestionJson
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class Question {
    @JvmField
    @SerializedName("id")
    val id: String? = null

    @JvmField
    @SerializedName("question")
    val question: String? = null

    @JvmField
    @SerializedName("description")
    val description: String? = null

    @JvmField
    @SerializedName("answers")
    val answers: MutableList<QuestionJson>? = null

    @JvmField
    @SerializedName("multiple_correct_answers")
    val multiple_correct_answers: String? = null

    @JvmField
    @SerializedName("correct_answers")
    val correct_answers:  MutableList<QuestionJson>? = null

    @JvmField
    @SerializedName("explanation")
    val explanation: String? = null

    @JvmField
    @SerializedName("tip")
    val tip: String? = null

    @JvmField
    @SerializedName("tags")
    val tags: String? = null

    @JvmField
    @SerializedName("category")
    val category: String? = null

    @JvmField
    @SerializedName("difficulty")
    val difficulty: String? = null
}