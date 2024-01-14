package model.core

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Metadata(
    @JsonProperty("query")
    val query: Query? = null
) : BaseResponse()

