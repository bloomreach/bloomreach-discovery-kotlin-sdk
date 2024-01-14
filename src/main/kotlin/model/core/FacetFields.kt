package model.core

import com.fasterxml.jackson.databind.JsonDeserializer
import model.BaseResponse
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = JsonDeserializer.None::class)
class FacetFields :  FacetValue()  {
    val name: String? = null
}

