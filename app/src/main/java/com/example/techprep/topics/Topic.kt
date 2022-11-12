package com.example.techprep.topics

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TopicsResponse(
    @SerialName("results")
    val response: List<Topic>? = null
) : java.io.Serializable

@Keep
@Serializable
data class Topic(
    @SerialName("category")
    val name : String? = null
) : java.io.Serializable