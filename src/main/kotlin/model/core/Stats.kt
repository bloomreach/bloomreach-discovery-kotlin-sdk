package model.core

import model.BaseResponse
import com.fasterxml.jackson.annotation.JsonProperty

data class Stats(
    @JsonProperty("stats_fields")
    val statsFields: LinkedHashMap<String, StatsField>? = null
) : BaseResponse() {
    fun getStatsField(key: String): StatsField {
        return statsFields?.get(key)!!
    }
}