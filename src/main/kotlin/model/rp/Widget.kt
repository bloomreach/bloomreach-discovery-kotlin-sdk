package model.rp

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class Widget(
    @JsonProperty("description")
    val description: String? = null,

    @JsonProperty("id")
    val id: String? = null,

    @JsonProperty("name")
    val name: String? = null,

    @JsonProperty("rid")
    val rid: String? = null,

    @JsonProperty("type")
    val type: String? = null
) : BaseResponse()