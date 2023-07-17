package com.airalo.assignment.data.model

import com.google.gson.annotations.SerializedName

data class ModelPackagesResponse(
    @field:SerializedName("image") val image: Image,
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("packages") val packages: List<PackagesItem>?,
    @field:SerializedName("slug") val slug: String? = null,
)

data class CountriesItem(
    @field:SerializedName("image") val image: Image? = null,
    @field:SerializedName("id") val id: Int? = null,
    @field:SerializedName("title") val title: String? = null,
    @field:SerializedName("slug") val slug: String? = null,
)

data class Operator(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("privacy_policy_url") val privacyPolicyUrl: Any? = null,
    @field:SerializedName("plan_type") val planType: String? = null,
    @field:SerializedName("apn_single") val apnSingle: String? = null,
    @field:SerializedName("gradient_start") val gradientStart: String? = null,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("type") val type: String? = null,
    @field:SerializedName("networks") val networks: List<NetworksItem?>? = null,
    @field:SerializedName("data_roaming") val dataRoaming: Boolean? = null,
    @field:SerializedName("activation_policy") val activationPolicy: String? = null,
    @field:SerializedName("kyc_type") val kycType: Any? = null,
    @field:SerializedName("gradient_end") val gradientEnd: String? = null,
    @field:SerializedName("rechargeability") val rechargeability: Boolean? = null,
    @field:SerializedName("is_multi_package") val isMultiPackage: Boolean? = null,
    @field:SerializedName("apn") val apn: Any? = null,
    @field:SerializedName("info") val info: List<String?>? = null,
    @field:SerializedName("is_kyc_verify") val isKycVerify: Int? = null,
    @field:SerializedName("image") val image: Image? = null,
    @field:SerializedName("other_info") val otherInfo: Any? = null,
    @field:SerializedName("is_prepaid") val isPrepaid: Boolean? = null,
    @field:SerializedName("apn_type") val apnType: String? = null,
    @field:SerializedName("apn_type_android") val apnTypeAndroid: String? = null,
    @field:SerializedName("countries") val countries: List<CountriesItem?>? = null,
    @field:SerializedName("apn_type_ios") val apnTypeIos: String? = null,
    @field:SerializedName("operator_legal_name") val operatorLegalName: Any? = null,
    @field:SerializedName("style") val style: String? = null,
)

data class NetworksItem(
    @field:SerializedName("service_type") val serviceType: String? = null,
    @field:SerializedName("network") val network: String? = null,
    @field:SerializedName("status") val status: Boolean? = null,
)

data class PackagesItem(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("data") val data: String,
    @field:SerializedName("is_stock") val isStock: Boolean,
    @field:SerializedName("note") val note: String? = null,
    @field:SerializedName("amount") val amount: Int? = null,
    @field:SerializedName("short_info") val shortInfo: String? = null,
    @field:SerializedName("type") val type: String? = null,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("is_unlimited") val isUnlimited: Boolean? = null,
    @field:SerializedName("operator") val operator: Operator? = null,
    @field:SerializedName("price") val price: Double,
    @field:SerializedName("fair_usage_policy") val fairUsagePolicy: String? = null,
    @field:SerializedName("calling_credit") val callingCredit: String? = null,
    @field:SerializedName("validity") val validity: String,
    @field:SerializedName("day") val day: Int? = null,
    @field:SerializedName("slug") val slug: String,
)

data class Image(
    @field:SerializedName("width") val width: Int? = null,
    @field:SerializedName("url") val url: String? = null,
    @field:SerializedName("height") val height: Int? = null,
)
