package model.core

import model.BaseResponse

data class StatsField(
    val min: Double = 0.0,
    val max: Double = 0.0
) : BaseResponse()