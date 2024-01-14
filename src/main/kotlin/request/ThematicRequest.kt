package request

import ApiConstants.DEFAULT_ROWS
import ApiConstants.DEFAULT_START
import ApiConstants.FL
import ApiConstants.REQUEST_TYPE
import ApiConstants.REQUEST_TYPE_SEARCH
import ApiConstants.SEARCH_TYPE
import ApiConstants.SEARCH_TYPE_KEYWORD

class ThematicRequest : RequestMap<ThematicRequest>() {

    // add default parameters required for search API
    init {
        rows(DEFAULT_ROWS)
        start(DEFAULT_START)
        setRequestType()
        setSearchType()
        wt("json")
    }

    /**
     * Method to set rows Search APIs
     *
     * @param rows The number of matching items to return per results page in the API response. The maximum value is 200.
     *
     * @return  A reference to the current Request object
     */
    fun rows(rows: Int): ThematicRequest {
        return set(ApiConstants.ROWS, rows.toString())
    }

    /**
     * Method to set start Search APIs
     *
     * @param start The number of the first item on a page of results. For example, the first item on the first page is 0, making the start value also 0.
     *
     * @return  A reference to the current Request object
     */
    fun start(start: Int): ThematicRequest {
        return set(ApiConstants.START, start.toString())
    }

    /**
     * Method to set hardcoded default parameters required for product search API
     * @return  A reference request object
     */
    private fun setRequestType(): ThematicRequest {
        return set(REQUEST_TYPE, REQUEST_TYPE_SEARCH)
    }

    /**
     * Method to set hardcoded default parameters required for product search API
     * @return  A reference request object
     */
    private fun setSearchType(): ThematicRequest {
        return set(SEARCH_TYPE, SEARCH_TYPE_KEYWORD)
    }

    /**
     * Method to set search term for Search APIs
     *
     * @param q  Query key for Searching
     *
     * @return  A reference to the current Request object
     */
    fun searchTerm(q: String): ThematicRequest {
        return set(ApiConstants.SEARCH_TERM, q)
    }

    /**
     * Method to set Field List for Search APIs
     *
     * @param value The comma separated attributes that you want returned in your API response, such as product IDs and prices.
     *
     * @return  A reference to the current Request object
     */
    fun fl(value: String): ThematicRequest {
        if (value.isEmpty()) {
            throw IllegalArgumentException("")
        }
        return set(FL, value)
    }

    /**
     * Method to set Field List for Search APIs
     *
     * @param values The attributes list that you want returned in your API response, such as product IDs and prices.
     *
     * @return  A reference to the current Request object
     */
    fun fl(values: List<String>): ThematicRequest {
        var flString = ""
        if (values.isNullOrEmpty()) {
            throw IllegalArgumentException()
        } else if (values.size == 1) {
            flString = values[0]
        } else if (values.size > 1) {
            flString = values.joinToString(",")
        }
        return fl(flString)
    }

    /**
     * Method to set Field List for Search APIs
     *
     * @param values The attributes array that you want returned in your API response, such as product IDs and prices.
     *
     * @return  A reference to the current Request object
     */
    fun fl(values: Array<String>): ThematicRequest {
        var flString = ""
        if (values.isNullOrEmpty()) {
            throw IllegalArgumentException()
        } else if (values.size == 1) {
            flString = values[0]
        } else if (values.size > 1) {
            flString = values.joinToString(",")
        }
        return fl(flString)
    }

    /**
     * Method to set url
     *
     * @param value url of the page
     *
     * @return  A reference to the current Request object
     */
    fun url(value: String): ThematicRequest {
        return set(ApiConstants.URL, value)
    }

    /**
     * Method to set wt
     *
     * @param value JSON or HTML
     *
     * @return  A reference to the current Request object
     */
    fun wt(value: String): ThematicRequest {
        return set("wt", value)
    }

    /**
     * Method to set debug
     *
     * @param value
     *
     * @return  A reference to the current Request object
     */
    fun debug(value: Boolean): ThematicRequest {
        return set("wt", value.toString())
    }

    /**
     * Method to set facet.range parameter
     * Use the facet.range parameter to include ranged facets
     *
     * @param value value for the facet range
     *
     * @return  A reference to the current Request object
     */
    fun facetRange(value: String?): ThematicRequest {
        return set(ApiConstants.FACET_RANGE, value)
    }

    /**
     * Method to set list of facet.range parameter
     * Use the facet.range parameter to include ranged facets
     *
     * @param values list for the facet range
     *
     * @return  A reference to the current Request object
     */
    fun facetRange(values: List<String>): ThematicRequest {
        var frString: String? = null
        if (values.isNullOrEmpty()) {
            frString = null
        } else if (values.size == 1) {
            frString = values[0]
        } else if (values.size > 1) {
            frString = values.joinToString(",")
        }
        return facetRange(frString)
    }

    /**
     * Method to set fq
     *  The fq parameter is an optional parameter that you can add to an API request to filter the results.
     *
     * @param value The formatted value to be passed to fq parameter
     *
     * @return  A reference to the current Request object
     */
    fun fq(value: String): ThematicRequest {
        return add(ApiConstants.FQ, value)
    }

    /**
     * Method to set fq with attribute and its single value
     * The fq parameter is an optional parameter that you can add to an API request to filter the results.
     *
     * @param attribute The attribute for fq
     * @param value The value of the attribute
     *
     * @return  A reference to the current Request object
     */
    fun fq(attribute: String, value: String): ThematicRequest {
        val fqValue = "$attribute:\"${value}\""
        return fq(fqValue)
    }

    /**
     * Method to set fq with attribute and its multiple value
     * The fq parameter is an optional parameter that you can add to an API request to filter the results.
     *
     * @param attribute The attribute for fq
     * @param values The list of multiple possible values for given attribute.
     *
     * @return  A reference to the current Request object
     */
    fun fq(attribute: String, values: List<String>): ThematicRequest {
        var str = ""
        if (values.size > 1) {
            for ((index, value) in values.withIndex()) {
                str += "\"${value}\""
                if (index != values.size - 1) {
                    str += " OR "
                }
            }
        } else {
            str += values[0]
        }
        return fq(attribute, str)
    }

    /**
     * The user agent of the BROWSER/MACHINE/DEVICE that's making the request.
     *
     * @param value user agent value
     *
     * @return  A reference request object
     */
    fun userAgent(value: String): ThematicRequest {
        return set(ApiConstants.USER_AGENT, value)
    }

    /**
     * Method to set user id of the customer
     *
     * @param value The universal customer ID of the user.
     *
     * @return  A reference to the current Request object
     */
    fun userId(value: String): ThematicRequest {
        return set(ApiConstants.USER_ID, value)
    }

    /**
     * Method to set userIp
     *
     * @param value The IP address of the browser that's loading the thematic page.
     *
     * @return  A reference to the current Request object
     */
    fun userIp(value: String): ThematicRequest {
        return set(ApiConstants.USER_IP, value)
    }
}