package com.airalo.assignment.data.mappers

import com.airalo.assignment.data.model.ModelPackagesResponse
import com.airalo.assignment.ui.packages.model.UIModelPackageInfo

object PackageInfoMapper {

    fun ModelPackagesResponse.toUIModelPackageInfo(): List<UIModelPackageInfo> {
        val defCountry = title
        return this.packages?.map {
            UIModelPackageInfo(
                id = it.id,
                title = it.title,
                data = it.data,
                validity = it.validity,
                image = it.operator?.image?.url,
                country = it.operator?.countries?.get(0)?.title ?: defCountry,
                operatorId = it.operator?.id,
                operator = it.operator?.title,
                slug = it.slug,
                countryId = it.operator?.countries?.get(0)?.id,
                price = it.price,
                inStoke = it.isStock,
            )
        } ?: emptyList()
    }
}
