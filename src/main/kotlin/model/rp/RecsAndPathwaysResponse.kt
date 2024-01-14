package model.rp

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class RecsAndPathwaysResponse(
    @JsonProperty("metadata")
    val metadata: Metadata? = null,

    @JsonProperty("response")
    val response: Response? = null
) : BaseResponse()