package model.core
import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Variant(
    @JsonProperty("skuid")
    val skuId: List<String>? = null
) : BaseResponse()