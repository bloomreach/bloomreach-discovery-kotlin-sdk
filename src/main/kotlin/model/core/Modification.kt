package model.core

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Modification(
    @JsonProperty("mode")
    val mode: String? = null,

    @JsonProperty("value")
    val value: String? = null
) : BaseResponse()