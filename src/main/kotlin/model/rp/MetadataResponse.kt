package model.rp

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class MetadataResponse(
    @JsonProperty("fallback")
    val fallback: String? = null,

    @JsonProperty("personalized_results")
    val personalizedResults: Boolean? = null,

    @JsonProperty("recall")
    val recall: String? = null
) : BaseResponse()
