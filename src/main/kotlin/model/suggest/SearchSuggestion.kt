package model.suggest

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse
import model.core.Variant

data class SearchSuggestion(
    @JsonProperty("pid")
    val pid: String? = null,

    @JsonProperty("sale_price")
    val salePrice: Double? = null,

    @JsonProperty("thumb_image")
    val thumbImage: String? = null,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("url")
    val url: String? = null,

    @JsonProperty("variants")
    val variants: List<Variant>? = null
) : BaseResponse()