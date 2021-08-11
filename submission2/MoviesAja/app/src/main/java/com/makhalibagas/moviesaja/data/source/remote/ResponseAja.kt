package com.makhalibagas.moviesaja.data.source.remote

import com.google.gson.annotations.SerializedName

data class ResponseAja<T>(
		@SerializedName("status_message")
		val statusMessage: String? = null,
		@SerializedName("status_code")
		val statusCode: Int? = null,
		@SerializedName("results")
		val results: List<T>? = null
)