import network.ApiProcessor
import request.AutosuggestRequest

/**
 * BrSuggestApi class holds method to initiate BrApiClient object and API calls methods
 */
class BrSuggestApi private constructor(
    brApiRequest: BrApiRequest) : BrApiClient(brApiRequest) {

    private val apiProcessor = ApiProcessor(brApiRequest, brHttpClient)

    class Builder : BrApiClient.Builder<BrSuggestApi>() {
        /**
         * @return BrSuggestApi instance
         */
        override fun build(): BrSuggestApi {
            return BrSuggestApi(BrApiRequest(accountId, uuid, visitorType, domainKey, authKey, userId, environment, baseUrl))
        }
    }

    /**
     * Method for calling Suggest API request
     * @param autoSuggestRequest Request Object required for AutosuggestRequest API
     *
     * @return  Any response object, SuggestResponse if API call success else return BrApiError object.
     */
    public fun autoSuggestApi(autoSuggestRequest: AutosuggestRequest): Any? {
        return apiProcessor.processSuggestApi(autoSuggestRequest.getMap())
    }
}