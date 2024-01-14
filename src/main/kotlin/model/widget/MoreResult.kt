package model.widget


import com.fasterxml.jackson.annotation.JsonProperty

data class MoreResult(
    @JsonProperty("attributes")
    val attributes: Attributes,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("image")
    val image: String,
    @JsonProperty("short-description")
    val shortDescription: String,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("url")
    val url: String
)