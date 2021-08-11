package com.makhalibagas.moviesaja.data.source.remote

import com.google.gson.annotations.SerializedName

data class TvShowsAja(

		@SerializedName("id")
		var id: Int = 0,
		@SerializedName("name")
		var name: String? = null,
		@SerializedName("overview")
		var desc: String? = null,
		@SerializedName("poster_path")
		var poster: String? = null,
		@SerializedName("backdrop_path")
		var img_preview: String? = null
)