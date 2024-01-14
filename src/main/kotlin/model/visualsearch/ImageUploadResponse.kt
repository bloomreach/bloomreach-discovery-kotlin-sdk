package model.visualsearch

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse

data class ImageUploadResponse(
    @JsonProperty("metadata")
    var metadata: Metadata? = null,
    @JsonProperty("response")
    var response: Response? = null
) : BaseResponse()