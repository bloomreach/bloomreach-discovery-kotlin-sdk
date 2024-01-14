import enums.ResponseType
import network.ApiProcessor
import request.*

/**
 * BrCoreApi class holds method to initiate BrApiClient object and API calls methods
 */
class BrCoreApi private constructor(
    brApiRequest: BrApiRequest) : BrApiClient(brApiRequest) {

    // create object for ApiProcessor
     private val apiProcessor = ApiProcessor(brApiRequest, brHttpClient)

    class Builder : BrApiClient.Builder<BrCoreApi>() {
        /**
         * Method to create object for BrCoreApi
         *
         * @return  BrCoreApi instance
         */
        override fun build(): BrCoreApi {
            return BrCoreApi(BrApiRequest(accountId, uuid, visitorType, domainKey, authKey, userId, environment, baseUrl))
        }
    }

    /**
     * Method for calling Product Search API request
     * @param productSearchRequest Request Object required for Product Search API
     *
     * @return  Any response object, CoreResponse if API call success else return BrApiError object
     */
    public fun productSearchApi(productSearchRequest: ProductSearchRequest): Any? {
        return apiProcessor.processCoreApi(productSearchRequest.getMap())
    }

    /**
     * Method for calling Category Search API request
     * @param categorySearchRequest Request Object required for Category Search API
     *
     * @return  Any response object, CoreResponse if API call success else return BrApiError object
     */
    public fun categorySearchApi(categorySearchRequest: CategorySearchRequest): Any? {
        return apiProcessor.processCoreApi(categorySearchRequest.getMap())
    }

    /**
     * Method for calling Category Search API request
     * @param categorySearchRequest Request Object required for Category Search API
     *
     * @return  Any response object, CoreResponse if API call success else return BrApiError object
     */
    public fun dynamicCategorySearchApi(categorySearchRequest: CategorySearchRequest): Any? {
        categorySearchRequest.setDynamicCategory()
        return apiProcessor.processCoreApi(categorySearchRequest.getMap())
    }

    /**
     * Method for calling Content API request
     * @param contentSearchRequest Request Object required for Content Search API
     *
     * @return  Any response object, CoreResponse if API call success else return BrApiError object
     */
    public fun contentSearchApi(contentSearchRequest: ContentSearchRequest): Any? {
        return apiProcessor.processCoreApi(contentSearchRequest.getMap())
    }

    /**
     * Method for calling BestSeller API request
     * @param bestSellerRequest Request Object required for Content Search API
     *
     * @return  Any response object, CoreResponse if API call success else return BrApiError object
     */
    public fun bestSellerApi(bestSellerRequest: BestSellerRequest):Any? {
        return apiProcessor.processCoreApi(bestSellerRequest.getMap())
    }

    /**
     * Method for calling Thematic API request
     * @param thematicRequest Request Object required for Thematic API
     * @param responseType Specify Response needed in JSON or HTML format, defaulted to JSON
     *
     * @return  Any response object, CoreResponse if API call success else return BrApiError object
     */
    public fun thematicApi(thematicRequest: ThematicRequest, responseType: ResponseType = ResponseType.JSON): Any? {
        return apiProcessor.processThematicApi(thematicRequest.getMap(), responseType)
    }

}