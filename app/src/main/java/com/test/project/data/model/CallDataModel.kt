package com.test.project.data.model

import com.google.gson.annotations.SerializedName

data class CallDataModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("number") var number: String? = null
)