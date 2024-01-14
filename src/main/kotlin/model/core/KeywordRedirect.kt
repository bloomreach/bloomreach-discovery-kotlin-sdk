package model.core

import model.BaseResponse

data class KeywordRedirect(
    val originalQuery: String? = null,
    val redirectedQuery: String? = null,
    val redirectedUrl: String? = null
) : BaseResponse()