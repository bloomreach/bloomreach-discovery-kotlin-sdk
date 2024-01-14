package model.rp

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Range(
    @JsonProperty("key")
    val key: String? = null,

    @JsonProperty("value")
    val value: List<Value>? = null,
) : BaseResponse()
