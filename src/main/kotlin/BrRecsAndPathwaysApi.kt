import enums.WidgetApiType
import network.ApiProcessor
import request.WidgetRequest
import java.io.InputStream

/**
 * BrRecsAndPathwaysApi class holds method to initiate BrApiClient object and API calls methods
 */
class BrRecsAndPathwaysApi private constructor(
    brApiRequest: BrApiRequest) : BrApiClient(brApiRequest) {

    private val apiProcessor = ApiProcessor(brApiRequest, brHttpClient)

    class Builder : BrApiClient.Builder<BrRecsAndPathwaysApi>() {
        /**
         * @return BrRecsAndPathwaysApi instance
         */
        override fun build(): BrRecsAndPathwaysApi {
            return BrRecsAndPathwaysApi(BrApiRequest(accountId, uuid, visitorType, domainKey, authKey, userId, environment, baseUrl))
        }
    }

    /**
     * Method for calling Recommendation Widget API where apiType can be specified
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param apiType the type of Recommendation Widget API. This is the widgetType path parameter
     * @param widgetRequest request Object required for Global Recommendation Widget API
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */
    public fun recAndPathwaysWidgetApi(widgetId: String, apiType: WidgetApiType, widgetRequest: WidgetRequest): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }

        return recAndPathwaysWidgetApi(widgetId, apiType.value, widgetRequest)
    }

    /**
     * Method for calling Recommendation Widget API where apiType can be specified
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param apiType the type of Recommendation Widget API. This is the widgetType path parameter
     * @param widgetRequest request Object required for Global Recommendation Widget API
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */
    private fun recAndPathwaysWidgetApi(widgetId: String,
                                apiType:String, widgetRequest: WidgetRequest): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        return apiProcessor.processRecsAndPathwaysApi(widgetId, apiType, widgetRequest.getMap())
    }

    /**
     * Method for calling Item-based Recommendation Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Item-based Recommendation Widget API
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */
    public fun itemBasedRecommendationWidgetApi(widgetId: String, widgetRequest: WidgetRequest): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        return recAndPathwaysWidgetApi(widgetId, WidgetApiType.ITEM.value, widgetRequest)
    }

    /**
     * Method for calling Category-based Recommendation Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Category-based Recommendation Widget API
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */
    public fun categoryBasedWidgetApi(widgetId: String, widgetRequest: WidgetRequest): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        return recAndPathwaysWidgetApi(widgetId, WidgetApiType.CATEGORY.value, widgetRequest)
    }

    /**
     * Method for calling Keyword-based Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Keyword-based Recommendation Widget API
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */

    public fun keywordBasedWidgetApi(widgetId: String, widgetRequest: WidgetRequest): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        return recAndPathwaysWidgetApi(widgetId, WidgetApiType.KEYWORD.value, widgetRequest)
    }


    /**
     * Method for calling Personalization-based Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Personalization-based Recommendation Widget API
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */

    public fun personalizationBasedWidgetApi(widgetId: String, widgetRequest: WidgetRequest): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        return recAndPathwaysWidgetApi(widgetId, WidgetApiType.PERSONALIZED.value, widgetRequest)
    }

    /**
     * Method for calling Global Recommendation Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Global Recommendation Widget API
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */
    public fun globalRecommendationWidgetApi(widgetId: String, widgetRequest: WidgetRequest): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        return recAndPathwaysWidgetApi(widgetId, WidgetApiType.GLOBAL.value, widgetRequest)
    }

    /**
     * Method to call Image Upload API for visual search and invoke the callback with appropriate result
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param inputStream inputStream of the image
     * @param fileName file name of the image
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */
    fun uploadImageForVisualSearch(widgetId: String, inputStream: InputStream, fileName:String): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        return apiProcessor.processVisualSearchUploadApi(widgetId, inputStream, fileName)
    }

    /**
     * Method for calling Visual Search Widget API
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param widgetRequest request Object required for Global Recommendation Widget API
     *
     * @return  Any response object, RecsAndPathwaysResponse if API call success else return BrApiError object
     */
    fun visualSearchWidgetApi(widgetId: String, widgetRequest: WidgetRequest): Any? {
        if(widgetId.isEmpty()) {
            throw IllegalArgumentException("Widget Id is empty")
        }
        return recAndPathwaysWidgetApi(widgetId, WidgetApiType.VISUAL_SEARCH.value, widgetRequest)
    }
}