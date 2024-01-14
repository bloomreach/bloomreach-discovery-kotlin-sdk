package request

/**
 * SeoWidget API Request Object class. Create the object of this class in order to
 * send it with SeoWidget API
 */
class SeoWidgetRequest : RequestMap<SeoWidgetRequest>() {

    /**
     * Method to set Acct Auth
     *
     * @param value The Bloomreach provided authentication key for your account ID.
     *
     * @return  A reference to the current Request object
     */
    fun acctAuth(value: String): SeoWidgetRequest {
        return set(ApiConstants.ACCT_AUTH, value)
    }

    /**
     * Method to set ptype
     *
     * @param value The page type.
     *
     * @return  A reference to the current Request object
     */
    fun pType(value: String): SeoWidgetRequest {
        return set(ApiConstants.P_TYPE, value)
    }

    /**
     * Method to set prod_id
     *
     * @param value The unique identifier for the product in your product catalog or database. This parameter affects product pages only. Not needed for category requests.
     *
     * @return  A reference to the current Request object
     */
    fun prodId(value: String): SeoWidgetRequest {
        return set(ApiConstants.PROD_ID, value)
    }

    /**
     * Method to set prod_name
     *
     * @param value The name of the product in your product catalog or database. Not needed for category requests.
     *
     * @return  A reference to the current Request object
     */
    fun prodName(value: String): SeoWidgetRequest {
        return set(ApiConstants.PROD_NAME, value)
    }

    /**
     * Method to set pstatus
     *
     * @param value The status of a product on the page. This parameter affects product pages only.
     *
     * @return  A reference to the current Request object
     */
    fun pStatus(value: String): SeoWidgetRequest {
        return set(ApiConstants.P_STATUS, value)
    }

    /**
     * Method to set url
     *
     * @param value The URL for the webpage that is sending the request.
     *
     * @return  A reference to the current Request object
     */
    fun url(value: String): SeoWidgetRequest {
        return set(ApiConstants.URL, value)
    }

    /**
     * The user agent of the BROWSER/MACHINE/DEVICE that's making the request.
     *
     * @param value user agent value
     *
     * @return  A reference request object
     */
    fun userAgent(value: String): SeoWidgetRequest {
        return set(ApiConstants.USER_AGENT, value)
    }

}