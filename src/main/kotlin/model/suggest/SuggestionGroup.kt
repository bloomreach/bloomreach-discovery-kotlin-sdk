package model.suggest

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class SuggestionGroup(
    @JsonProperty("attributeSuggestions")
    val attributeSuggestions: List<AttributeSuggestion>? = null,

    @JsonProperty("catalogName")
    val catalogName: String? = null,

    @JsonProperty("querySuggestions")
    val querySuggestions: List<QuerySuggestion>? = null,

    @JsonProperty("searchSuggestions")
    val searchSuggestions: List<SearchSuggestion>? = null,

    @JsonProperty("view")
    val view: String? = null
) : BaseResponse()