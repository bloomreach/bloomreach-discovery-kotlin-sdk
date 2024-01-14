package model.suggest

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class SuggestResponse(
    @JsonProperty("queryContext")
    val queryContext: QueryContext? = null,

    @JsonProperty("suggestionGroups")
    val suggestionGroups: List<SuggestionGroup>? = null
) : BaseResponse()