package model.suggest

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class AttributeSuggestion(
    @JsonProperty("attributeType")
    val attributeType: String? = null,

    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("value")
    val value: String? = null
) : BaseResponse()