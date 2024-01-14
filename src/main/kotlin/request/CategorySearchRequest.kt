package request

class CategorySearchRequest() : SearchRequest<CategorySearchRequest>() {

    // add hardcoded default parameters required for Category Search API
    init {
        setRequestType()
        setSearchType()
    }

    /**
     * Method to set hardcoded default parameters required for Category Search API
     * @return  A reference request object
     */
    private fun setRequestType(): CategorySearchRequest {
        return set(ApiConstants.REQUEST_TYPE, ApiConstants.REQUEST_TYPE_SEARCH)
    }

    /**
     * Method to set hardcoded default parameters required for Category Search API
     * @return  A reference request object
     */
    private fun setSearchType(): CategorySearchRequest {
        return set(ApiConstants.SEARCH_TYPE, ApiConstants.SEARCH_TYPE_CATEGORY)
    }

    /**
     * Method to set hardcoded default parameters required for Category Search API
     * @return  A reference request object
     */
    internal fun setDynamicCategory(): CategorySearchRequest {
        return set(ApiConstants.CATEGORY_TYPE, ApiConstants.CATEGORY_TYPE_DYNAMIC)
    }

}