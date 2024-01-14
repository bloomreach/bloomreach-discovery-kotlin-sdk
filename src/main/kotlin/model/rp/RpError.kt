package model.rp

import com.fasterxml.jackson.annotation.JsonProperty

data class RpError(
    @JsonProperty("detail")
    val detail: String? = null,
)
