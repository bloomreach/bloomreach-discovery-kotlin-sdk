package network

import enums.VisitorType
import org.apache.hc.core5.net.URIBuilder
import java.net.URLDecoder
import kotlin.random.Random

/**
 * Utility class for performing string formatting operations
 */
internal object FormatterUtils {
    /**
     * Method to generate a random number
     *
     * @return random number in string
     */
    fun generateRand(): String {
        // generate random Long
        return Random.nextLong().toString()
    }

    /**
     * Method to format Cookie2 value
     * @param uuid Android random string
     * @param hitcount ENUM VisitorType (The hitcount value should be 1 for a new visitor, or 2 for returning visitors.)
     *
     * @return  cookie2 - String  value in 'uid={{UUID}}:v=app:ts=0:hc={{hitcount}}' format
     */
    fun formatCookieValue(uuid: String, hitcount: VisitorType): String {
        // convert uid={{UUID}}:v=app:ts=0:hc={{hitcount}}
        return "uid=$uuid:v=DiscoveryClientTools-Java-1.0:ts=0:hc=${hitcount.hitCount}"  //DiscoveryClientTools-1.0 //TODO
    }

    /**
     * Method to convert Map to Uri.Builder for network calls
     *
     * @param queryMap array of the PixelBasketItem objects
     * @return  Uri.Builder - String value in required format
     */
    fun mapToUriBuilderForApi(queryMap: MutableMap<String, Any?>): URIBuilder {
        val uriBuilder = URIBuilder()
        queryMap.forEach { mapObject ->
            if(mapObject.value is String) {
                uriBuilder.setParameter(mapObject.key, getUrlDecodeString(mapObject.value as String))
            } else if(mapObject.value is List<*>) { // check for fq
                for(value in mapObject.value as List<*>) {
                    uriBuilder.setParameter(mapObject.key, getUrlDecodeString(value as String))
                }
            }
        }
        return uriBuilder
    }

    private fun getUrlDecodeString(value: String): String {
        return URLDecoder.decode(value, "UTF-8")
    }
}