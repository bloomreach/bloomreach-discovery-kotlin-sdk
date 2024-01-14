package model.core

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = FacetValueDeserializer::class)
open class FacetValue : BaseResponse() {

//    @JsonProperty("cat_id")
//    val catId: String? = null
//
//    @JsonProperty("cat_name")
//    val catName: String? = null
//
//    @JsonProperty("crumb")
//    val crumb: String? = null
//
//    @JsonProperty("tree_path")
//    val treePath: String? = null
//
//    @JsonProperty("parent")
//    val parent: String? = null
//
//    val start: Any? = null
//    val end: Any? = null
//
//    val name: String? = null

    val count: Int? = null
}