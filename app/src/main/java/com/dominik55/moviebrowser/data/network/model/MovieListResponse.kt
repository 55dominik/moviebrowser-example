package com.dominik55.moviebrowser.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

    @SerializedName("page")
    val page: Int? = null,
    
    @SerializedName("results") 
    val results: ArrayList<MovieResult> = arrayListOf(),
    
    @SerializedName("total_results") 
    val totalResults: Int? = null,
    
    @SerializedName("total_pages") 
    val totalPages: Int? = null
)
