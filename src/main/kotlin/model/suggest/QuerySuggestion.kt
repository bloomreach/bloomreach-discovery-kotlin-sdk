package model.suggest

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class QuerySuggestion(
    @JsonProperty("displayText")
    val displayText: String? = null,

    @JsonProperty("query")
    val query: String? = null
) : BaseResponse()