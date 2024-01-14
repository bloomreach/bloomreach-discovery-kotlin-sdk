package model.rp

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Metadata(
    @JsonProperty("response")
    val response: MetadataResponse? = null,

    @JsonProperty("widget")
    val widget: Widget? = null
)  : BaseResponse()