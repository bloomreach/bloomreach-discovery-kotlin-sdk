package model.visualsearch

import com.fasterxml.jackson.annotation.JsonProperty
import model.BaseResponse
import model.rp.Widget
data class Metadata(
    @JsonProperty("query")
    var query: Query? = null,
    @JsonProperty("widget")
    var widget: Widget? = null
) : BaseResponse()
