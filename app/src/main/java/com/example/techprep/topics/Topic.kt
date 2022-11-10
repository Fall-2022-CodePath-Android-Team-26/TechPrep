package com.example.techprep.topics

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TopicResponse(
    @SerialName("results")
    val response: List<Topic>
)

@Keep
@Serializable
data class Topic(
    @SerialName("category")
    val name : String?) : java.io.Serializable