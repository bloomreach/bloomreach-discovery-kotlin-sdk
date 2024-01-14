package network

import BrApiRequest
import com.fasterxml.jackson.databind.ObjectMapper
import enums.ApiType
import enums.ResponseType
import model.BrApiError
import model.core.CoreResponse
import model.rp.RecsAndPathwaysResponse
import model.rp.RpError
import model.suggest.SuggestResponse
import model.widget.WidgetResponse
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.core5.http.ClassicHttpRequest
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder
import java.net.URI
import org.slf4j.LoggerFactory

/**
 *  Class to perform API calls
 */
internal class BrHttpClient(private val client: CloseableHttpClient) {

    private val logger = LoggerFactory.getLogger(BrHttpClient::class.java.name)
    /**
     * Method to HTTP call for all API
     * @param url URL object containing request parameters
     * @param type The type to identify of API call  such as core, suggest.
     * @param brApiRequest Object of all global paramters to be sent with the API
     * @param responseType Response Type JSON or HTML
     *
     * @return  Any response object, Based on ApiType and ResponseType
     */
    fun invoke(
        url: URI,
        type: ApiType,
        brApiRequest: BrApiRequest,
        responseType: ResponseType = ResponseType.JSON
    ): Any? {

        logger.trace("invoke() - ApiType: ${type}, responseType: $responseType")
        logger.trace("url: ${url.toURL()}")

        val request: ClassicHttpRequest = ClassicRequestBuilder
            .get()
            .setUri(url)
            .addHeader("Cache-Control", "no-cache")
            .build()

        try {
            client.execute(request).use { response ->
                logger.info("API response code: ${response.code} ")
                // check if response code is 200
                if (response.code in 200..299) {
                    val entity = response.entity
                    if (entity != null) {
                        // get response as a String
                        val result = EntityUtils.toString(entity)
                        logger.trace("Response: $result")

                        return if (responseType == ResponseType.JSON) {
                            // if response type is JSON, do the jackson parsing
                            val responseMapper = ObjectMapper()
                            when (type) {
                                // if type matches do the parsing to respective model
                                ApiType.CORE -> responseMapper.readValue(result, CoreResponse::class.java)
                                ApiType.WIDGET -> responseMapper.readValue(result, WidgetResponse::class.java)
                                ApiType.SUGGEST -> responseMapper.readValue(result, SuggestResponse::class.java)
                                ApiType.PATHWAYS -> responseMapper.readValue(
                                    result,
                                    RecsAndPathwaysResponse::class.java
                                )
                            }
                        } else {
                            // return the HTML response as String
                            result
                        }
                    } else {
                        logger.error("Error code: ${response.code}, Error Message: ${response.reasonPhrase}")
                        // return error with response code and reason
                        BrApiError(response.reasonPhrase, response.code)
                    }
                } else {
                    // return error with response code and reason
                    val entity = response.entity
                    if (entity != null) {
                        // get response as a String
                        val result = EntityUtils.toString(entity)
                        logger.trace("Response: $result")

                        return if (type == ApiType.PATHWAYS) {
                            val responseMapper = ObjectMapper()
                            val rpError = responseMapper.readValue(result, RpError::class.java)
                            BrApiError(rpError.detail ?: "Something went wrong", response.code)
                        } else {
                            logger.error("Error code: ${response.code}, Error Message: ${response.reasonPhrase}")
                            val responseMapper = ObjectMapper()
                            responseMapper.readValue(result, BrApiError::class.java)
                        }

                    } else {
                        BrApiError(response.reasonPhrase, response.code)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // return error with exception message
            BrApiError(e.localizedMessage, 0)
        }
        return BrApiError("Something went wrong, please try again", -1)
    }
}