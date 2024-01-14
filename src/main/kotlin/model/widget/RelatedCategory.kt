package model.widget


import com.fasterxml.jackson.annotation.JsonProperty

data class RelatedCategory(
    @JsonProperty("anchor")
    val anchor: String,
    @JsonProperty("url")
    val url: String
)