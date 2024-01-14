package model.core

import com.fasterxml.jackson.annotation.JsonFormat
import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty
data class Facet(
    @JsonProperty("name")
    var name: String? = null,
    @JsonProperty("type")
    var type: String? = null,
    @JsonFormat(with = arrayOf(JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
    @JsonProperty("value")
    var value: ArrayList<FacetValue>? = null
): BaseResponse()
