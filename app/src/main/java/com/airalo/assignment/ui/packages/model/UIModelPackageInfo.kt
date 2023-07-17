package com.airalo.assignment.ui.packages.model

data class UIModelPackageInfo(
    val id: Int,
    val title: String,
    val data: String,
    val validity: String,
    val image: String?,
    val country: String,
    val operator: String?,
    val slug: String,
    val operatorId: Int?,
    val countryId: Int?,
    val price: Double,
    val inStoke: Boolean,
)
