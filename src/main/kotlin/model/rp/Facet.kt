package model.rp

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Facet(
    @JsonProperty("category")
    val category: List<Category>? = null,

    @JsonProperty("fields")
    val fields: List<Field>? = null,

    @JsonProperty("ranges")
    val ranges: List<Range>? = null,
) : BaseResponse()
