package com.airalo.assignment.ui.packages.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airalo.assignment.core.utils.load
import com.airalo.assignment.databinding.RowItemPackagesBinding
import com.airalo.assignment.ui.packages.model.UIModelPackageInfo

class PackagesAdapter :
    RecyclerView.Adapter<PackagesAdapter.PackagesViewHolder>() {

    private val packages: ArrayList<UIModelPackageInfo> = arrayListOf()

    fun setItems(uiModelPackagesInfo: List<UIModelPackageInfo>) {
        packages.clear()
        packages.addAll(uiModelPackagesInfo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagesViewHolder {
        val binding = RowItemPackagesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return PackagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PackagesViewHolder, position: Int) {
        holder.bind(packages[position])
    }

    override fun getItemCount() = packages.size

    inner class PackagesViewHolder(private val rowItemPackagesBinding: RowItemPackagesBinding) :
        RecyclerView.ViewHolder(rowItemPackagesBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(uIModelPackageInfo: UIModelPackageInfo) {
            rowItemPackagesBinding.apply {
                tvOperatorName.text = uIModelPackageInfo.operator
                tvCompanyOrigin.text = uIModelPackageInfo.country
                tvPackageData.text = uIModelPackageInfo.data
                tvPackageValidity.text = uIModelPackageInfo.validity
                btnBuyNow.text = "US $${uIModelPackageInfo.price} - BUY NOW"
                operatorLogo.load(uIModelPackageInfo.image)
            }
        }
    }
}
