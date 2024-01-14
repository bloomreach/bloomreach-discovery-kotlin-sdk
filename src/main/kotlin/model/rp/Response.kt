package model.rp

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Response(
    @JsonProperty("docs")
    val docs: List<Doc>? = null,

    @JsonProperty("numFound")
    val numFound: Int? = null,

    @JsonProperty("start")
    val start: Int? = null
) : BaseResponse()
