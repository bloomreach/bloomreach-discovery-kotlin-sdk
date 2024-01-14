package model.core

import com.fasterxml.jackson.annotation.JsonFormat
import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class FacetCounts(

    @Deprecated("Customers whose go-live date is after September 7, 2023 will be on V3 Facet response format by default. " +
            "If you’re on the legacy format and would like to implement the new Facet response format, kindly contact your Bloomreach Services representative.")
    @JsonProperty("facet_fields")
    val facetFields: LinkedHashMap<String, List<Any>>? = null,

    @Deprecated("Customers whose go-live date is after September 7, 2023 will be on V3 Facet response format by default. " +
            "If you’re on the legacy format and would like to implement the new Facet response format, kindly contact your Bloomreach Services representative.")
    @JsonProperty("facet_queries")
    val facetQueries: LinkedHashMap<String, List<FacetFields>>? = null,

    @Deprecated("Customers whose go-live date is after September 7, 2023 will be on V3 Facet response format by default. " +
            "If you’re on the legacy format and would like to implement the new Facet response format, kindly contact your Bloomreach Services representative.")
    @JsonProperty("facet_ranges")
    val facetRanges: LinkedHashMap<String, List<FacetRange>>? = null,

    @JsonProperty("facets")
    val facets: List<Facet>? = null
) : BaseResponse()