package model.suggest

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class QueryContext(
    @JsonProperty("originalQuery")
    val originalQuery: String? = null
) : BaseResponse()