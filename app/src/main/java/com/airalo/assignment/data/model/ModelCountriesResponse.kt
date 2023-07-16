package com.airalo.assignment.data.model

import com.google.gson.annotations.SerializedName

data class ModelCountriesResponse(
    @field:SerializedName("package_count") val packageCount: Int,
    @field:SerializedName("image") val image: Image? = null,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("slug") val slug: String,
) {
    data class Image(
        @field:SerializedName("width") val width: Int? = null,
        @field:SerializedName("url") val url: String? = null,
        @field:SerializedName("height") val height: Int? = null,
    )
}
