import enums.ResponseType
import network.ApiProcessor
import request.SeoWidgetRequest

/**
 * BrSeoWidgetApi class holds method to initiate BrApiClient object and API calls methods
 */
class BrSeoWidgetApi private constructor(
    brApiRequest: BrApiRequest
) : BrApiClient(brApiRequest) {

    private val apiProcessor = ApiProcessor(brApiRequest, brHttpClient)

    class Builder : BrApiClient.Builder<BrSeoWidgetApi>() {
        /**
         * @return  BrSeoWidgetApi instance
         */
        override fun build(): BrSeoWidgetApi? {
            return BrSeoWidgetApi(
                BrApiRequest(
                    accountId,
                    uuid,
                    visitorType,
                    domainKey,
                    authKey,
                    userId,
                    environment,
                    baseUrl
                )
            )
        }
    }

    /**
     * Method for calling SEO Widget API request
     * @param endpoint Base Url for the API call
     * @param seoWidgetRequest Base Url for the API call
     * @param responseType Specify Response needed in JSON or HTML format, defaulted to JSON
     *
     * @return  Any response object, WidgetResponse or HTML if API call success else return BrApiError object.
     */
    public fun seoWidgetApi(
        seoWidgetRequest: SeoWidgetRequest,
        responseType: ResponseType = ResponseType.JSON
    ): Any? {
        return apiProcessor.processWidgetApi(seoWidgetRequest.getMap(), responseType)
    }
}