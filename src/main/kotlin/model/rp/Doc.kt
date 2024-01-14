package model.rp

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse
import model.core.Variant

data class Doc(
    @JsonProperty("brand")
    val brand: String? = null,

    @JsonProperty("description")
    val description: String? = null,

    @JsonProperty("pid")
    val pid: String? = null,

    @JsonProperty("price")
    val price: Double? = null,

    @JsonProperty("sale_price")
    val salePrice: Double? = null,

    @JsonProperty("thumb_image")
    val thumbImage: String? = null,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("url")
    val url: String? = null,

    @JsonProperty("variants")
    val variants: List<Variant>? = null,
) : BaseResponse()
