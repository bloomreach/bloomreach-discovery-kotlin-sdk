package network

import BrApiRequest
import enums.ApiType
import enums.Env
import enums.ResponseType
import enums.VisitorType
import model.BrApiError
import model.visualsearch.ImageUploadResponse
import org.apache.hc.core5.net.URIBuilder
import java.io.InputStream
import java.net.URI
import java.net.URL

/**
 * Class for adding global parameters to the request, processing all types of API call and providing callback once API returns
 */
internal class ApiProcessor(
    brApiRequest: BrApiRequest,
    private val brHttpClient: BrHttpClient
) {
    lateinit var brApiRequestData: BrApiRequest

    init {
        this.brApiRequestData = brApiRequest
    }

    private val CORE_API_PATH = "api/v1/core/"
    private val SUGGEST_API_PATH = "api/v2/suggest"
    private val SEO_WIDGET_API_PATH = "v3/fetch_widget"
    private val WIDGET_API_PATH = "api/v2/widgets/"
    private val SCEHEME = "https"

    /**
     * Method to format Core API parameters, execute the API and invoke the callback with appropriate result
     * @param params Map of request parameters to be sent with the request
     *
     * @return CoreResponse if API call success else return BrApiError object.
     */
    fun processCoreApi(params: MutableMap<String, Any?>): Any? {
        var uriBuilder = FormatterUtils.mapToUriBuilderForApi(params)
//        add global request parameters
        uriBuilder = addGlobalQuery(uriBuilder)
//         append base endpoint for API call
        addBaseUrlForCoreApi(uriBuilder)
        //perform API
        return brHttpClient.invoke(uriBuilder.build(), ApiType.CORE, brApiRequestData)
    }

    /**
     * Method to format Thematic API parameters, execute the API and invoke the callback with appropriate result
     * @param params Map of request parameters to be sent with the request
     * @param responseType Response in JSON or HTML, defaulted to JSON
     *
     * @return CoreResponse or HTML if API call success else return BrApiError object.
     */
    fun processThematicApi(params: MutableMap<String, Any?>, responseType: ResponseType = ResponseType.JSON): Any? {
        var uriBuilder = FormatterUtils.mapToUriBuilderForApi(params)
//        add global request parameters
        uriBuilder = addGlobalQuery(uriBuilder)
//         append base endpoint for API call
        addBaseUrlForCoreApi(uriBuilder)
        //perform API
        return brHttpClient.invoke(uriBuilder.build(), ApiType.CORE, brApiRequestData, responseType)
    }

    /**
     * Method to format Suggest API parameters, execute the API and invoke the callback with appropriate result
     * @param params Map of request parameters to be sent with the request
     *
     * @return SuggestResponse if API call success else return BrApiError object.
     */
    fun processSuggestApi(
        params: MutableMap<String, Any?>
    ): Any? {
        var uriBuilder = FormatterUtils.mapToUriBuilderForApi(params)
//        add global request parameters
        uriBuilder = addGlobalQuery(uriBuilder)
//         append base endpoint for API call
        addBaseUrlForSuggestApi(uriBuilder)
        //perform API
        return brHttpClient.invoke(uriBuilder.build(), ApiType.SUGGEST, brApiRequestData)
    }

    /**
     * Method to format SEO Widget API parameters, execute the API and invoke the callback with appropriate result
     * @param params Map of request parameters to be sent with the request
     * @param responseType Response in JSON or HTML, defaulted to JSON
     *
     * @return WidgetResponse or HTML if API call success else return BrApiError object.
     */
    fun processWidgetApi(params: MutableMap<String, Any?>, responseType: ResponseType = ResponseType.JSON): Any? {
        var uriBuilder = FormatterUtils.mapToUriBuilderForApi(params)
//        add acct_id manually here as the key name is different
        uriBuilder.setParameter("acct_id", brApiRequestData.accountId)
//         append base endpoint for API call
        addBaseUrlForWidgetApi(uriBuilder)

//       set the output format key based on the response type required
        if (responseType == ResponseType.JSON) {
            uriBuilder.setParameter("output_format", "json")
        } else {
            uriBuilder.setParameter("output_format", "html")
        }
//      perform API
        return brHttpClient.invoke(uriBuilder.build(), ApiType.WIDGET, brApiRequestData, responseType)
    }

    /**
     * Method to format Core API parameters, execute the API and invoke the callback with appropriate result
     * @param params Map of request parameters to be sent with the request
     * @param responseType Response in JSON or HTML, defaulted to JSON
     *
     * @return RecsAndPathwaysResponse if API call success else return BrApiError object.
     */
    fun processRecsAndPathwaysApi(
        widgetId: String,
        widgetType: String, params: MutableMap<String, Any?>
    ): Any? {
        var uriBuilder = FormatterUtils.mapToUriBuilderForApi(params)
//        add global request parameters
        uriBuilder = addGlobalQuery(uriBuilder)
//         append base endpoint for API call
        addBaseUrlForPathwaysApi(uriBuilder, widgetType, widgetId)
        //perform API
        return brHttpClient.invoke(uriBuilder.build(), ApiType.PATHWAYS, brApiRequestData)
    }

    /**
     * Method to call Image Upload API for visual search and invoke the callback with appropriate result
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     * @param inputStream inputStream of the image
     * @param filename file name of the image
     */
    fun processVisualSearchUploadApi(
        widgetId: String,
        inputStream: InputStream,
        filename: String
    ): Any? {
        var uriBuilder = URIBuilder()
        //  add global request parameters
        uriBuilder = addGlobalQuery(uriBuilder)
        // append base endpoint for Pixel call
        addBaseUrlForPathwaysApi(uriBuilder, "visual/upload", widgetId)
        //perform API
        val uploadFile =
            FileUpload(URL(uriBuilder.build().toString()), brApiRequestData.authKey ?: "")
        uploadFile.addFilePart(inputStream, filename)
        val result = uploadFile.upload()
        return result
    }

    /**
     * Method to add global request parameters to Uri Builder
     * @param uriBuilder The Uri.Builder where the global request parameters will be added in required format
     */
    fun addGlobalQuery(uriBuilder: URIBuilder): URIBuilder {
        uriBuilder.setParameter("account_id", brApiRequestData.accountId)
        uriBuilder.setParameter("auth_key", brApiRequestData.authKey)
        uriBuilder.setParameter("domain_key", brApiRequestData.domainKey)
        uriBuilder.setParameter("request_id", FormatterUtils.generateRand())
        uriBuilder.setParameter(
            "_br_uid_2",
            FormatterUtils.formatCookieValue(
                brApiRequestData.uuid ?: "",
                brApiRequestData.visitorType ?: VisitorType.NEW_USER
            )
        )
        if (!brApiRequestData.userId.isNullOrEmpty()) {
            uriBuilder.setParameter("user_id", brApiRequestData.userId)
        }
        return uriBuilder
    }

    /**
     * Method to generate Base EndPoint Url for Stage Env
     *
     * @param uriBuilder The Uri.Builder the Base Url Endpoint will be set
     *
     */
    fun addBaseUrlForCoreApi(uriBuilder: URIBuilder) {
        if (!brApiRequestData.baseUrl.isNullOrEmpty()) {
            val uri = URI(brApiRequestData.baseUrl)
            uriBuilder.scheme = uri.scheme
            uriBuilder.host = uri.host
        } else {
            uriBuilder.scheme = SCEHEME
            //check for env if stage or prod
            when (brApiRequestData.environment) {
                Env.STAGE -> uriBuilder.host = "staging-core.dxpapi.com"
                Env.PROD -> uriBuilder.host = "core.dxpapi.com"
            }
        }
        uriBuilder.path = CORE_API_PATH

    }

    /**
     * Method to generate Base EndPoint Url for Stage Env
     *
     * @param uriBuilder The Uri.Builder the Base Url Endpoint will be set
     *
     */
    fun addBaseUrlForSuggestApi(uriBuilder: URIBuilder) {
        if (!brApiRequestData.baseUrl.isNullOrEmpty()) {
            val uri = URI(brApiRequestData.baseUrl)
            uriBuilder.scheme = uri.scheme
            uriBuilder.host = uri.host
        } else {
            uriBuilder.scheme = SCEHEME
            //check for env if stage or prod
            when (brApiRequestData.environment) {
                Env.STAGE -> uriBuilder.host = "staging-suggest.dxpapi.com"
                Env.PROD -> uriBuilder.host = "suggest.dxpapi.com"
            }
        }
        uriBuilder.path = SUGGEST_API_PATH
    }

    /**
     * Method to generate Base EndPoint Url for SEO widget API
     *
     * @param uriBuilder The Uri.Builder the Base Url Endpoint will be set
     */
    fun addBaseUrlForWidgetApi(uriBuilder: URIBuilder) {
        if (!brApiRequestData.baseUrl.isNullOrEmpty()) {
            val uri = URI(brApiRequestData.baseUrl)
            uriBuilder.scheme = uri.scheme
            uriBuilder.host = uri.host
        } else {
            uriBuilder.scheme = SCEHEME
            //check for env if stage or prod
            when (brApiRequestData.environment) {
                Env.STAGE -> uriBuilder.host = "bsapi-test.brsrvr.com"
                Env.PROD -> {
                    throw IllegalArgumentException("Base Url is not provided")
                }
            }
        }
        uriBuilder.path = SEO_WIDGET_API_PATH
    }

    /**
     * Method to generate Base EndPoint Url for Stage Env
     *
     * @param uriBuilder The Uri.Builder the Base Url Endpoint will be set
     * @param widgetType Type of widget
     * @param widgetId The ID of the widget, which can be found in the Widget Configurator in the Dashboard.
     *
     */
    fun addBaseUrlForPathwaysApi(uriBuilder: URIBuilder, widgetType: String, widgetId: String) {
        if (!brApiRequestData.baseUrl.isNullOrEmpty()) {
            val uri = URI(brApiRequestData.baseUrl)
            uriBuilder.scheme = uri.scheme
            uriBuilder.host = uri.host
        } else {
            uriBuilder.scheme = SCEHEME
            //check for env if stage or prod
            when (brApiRequestData.environment) {
                Env.STAGE -> uriBuilder.host = "pathways-staging.dxpapi.com"
                Env.PROD -> uriBuilder.host = "pathways.dxpapi.com"
            }
        }
        uriBuilder.path = "$WIDGET_API_PATH$widgetType/$widgetId"
    }

}