package model.widget


import com.fasterxml.jackson.annotation.JsonProperty

data class Attributes(
    @JsonProperty("brand")
    val brand: String,
    @JsonProperty("reviews_attr")
    val reviewsAttr: String,
    @JsonProperty("reviews_attr_count")
    val reviewsAttrCount: String
)