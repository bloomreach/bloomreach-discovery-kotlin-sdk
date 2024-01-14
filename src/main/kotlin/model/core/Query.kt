package model.core

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Query(
    @JsonProperty("modification")
    val modification: Modification? = null,

    @JsonProperty("didYouMean")
    val didYouMean: List<String>? = null
) : BaseResponse()