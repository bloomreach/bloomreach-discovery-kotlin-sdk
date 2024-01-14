package model.visualsearch

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class Response(
    @JsonProperty("image_id")
    var imageId: String? = null
) : BaseResponse()
