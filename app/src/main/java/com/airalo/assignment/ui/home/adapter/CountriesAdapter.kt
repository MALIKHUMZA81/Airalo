package com.airalo.assignment.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airalo.assignment.core.utils.load
import com.airalo.assignment.databinding.RowItemCountriesBinding
import com.airalo.assignment.ui.home.model.UIModelCountry

class CountriesAdapter :
    RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    private lateinit var onCountrySelection: (UIModelCountry) -> Unit
    private val countries: ArrayList<UIModelCountry> = arrayListOf()

    fun setItems(uiModelCountries: List<UIModelCountry>) {
        countries.clear()
        countries.addAll(uiModelCountries)
        notifyDataSetChanged()
    }

    fun onCountrySelectionListener(listener: (UIModelCountry) -> Unit) {
        onCountrySelection = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val binding = RowItemCountriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return CountriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size

    inner class CountriesViewHolder(private val rowItemCountriesBinding: RowItemCountriesBinding) :
        RecyclerView.ViewHolder(rowItemCountriesBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(uIModelCountry: UIModelCountry) {
            rowItemCountriesBinding.apply {
                tvCountryName.text = uIModelCountry.title
                imgCountryFlag.load(imageUrl = uIModelCountry.flagImage)
                imgPackageInfo.setOnClickListener {
                    onCountrySelection.invoke(uIModelCountry)
                }
            }
        }
    }
}
