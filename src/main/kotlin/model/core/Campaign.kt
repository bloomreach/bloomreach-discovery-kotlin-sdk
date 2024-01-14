package model.core

import model.BaseResponse

data class Campaign(
    val id: String? = null,
    val htmlText: String? = null,
    val bannerType: String? = null,
    val keyword: String? = null,
    val name: String? = null,
    val dateEnd: String? = null,
    val dateStart: String? = null
) : BaseResponse()