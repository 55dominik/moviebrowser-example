package com.dominik55.moviebrowser.data.network.model

import com.google.gson.annotations.SerializedName

data class ProductionCompany(

    @SerializedName("id") var id: Int,
    @SerializedName("logo_path") var logoPath: String? = null,
    @SerializedName("name") var name: String,
    @SerializedName("origin_country") var originCountry: String? = null

)