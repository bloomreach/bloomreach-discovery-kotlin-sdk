package model.rp

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Value(
    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("count")
    val count: Int = 0,

    @JsonProperty("start")
    val start: Any? = null,

    @JsonProperty("end")
    val end: Any? = null,

) : BaseResponse()
