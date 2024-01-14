package model.widget


import com.fasterxml.jackson.annotation.JsonProperty

data class RelatedItem(
    @JsonProperty("anchor")
    val anchor: String,
    @JsonProperty("url")
    val url: String
)