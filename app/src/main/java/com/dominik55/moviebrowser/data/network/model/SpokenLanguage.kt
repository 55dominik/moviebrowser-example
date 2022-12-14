package com.dominik55.moviebrowser.data.network.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(

    @SerializedName("iso_639_1") var iso6391: String,
    @SerializedName("name") var name: String

)