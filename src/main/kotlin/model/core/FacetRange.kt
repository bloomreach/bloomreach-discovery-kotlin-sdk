package model.core

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import model.BaseResponse

@JsonDeserialize(using = JsonDeserializer.None::class)
data class FacetRange(
    val start: Any? = null,
    val end: Any? = null,
) : FacetValue()
