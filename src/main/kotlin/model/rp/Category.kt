package model.rp

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class Category(
    @JsonProperty("cat_id")
    val catId: String? = null,

    @JsonProperty("cat_name")
    val catName: String? = null,

    @JsonProperty("count")
    val count: Int = 0,

    @JsonProperty("children")
    val children: List<Category>? = null
) : BaseResponse()
