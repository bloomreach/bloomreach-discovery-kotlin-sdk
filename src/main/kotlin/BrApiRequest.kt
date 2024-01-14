import enums.Env
import enums.VisitorType

/**
 * Class containing initialising parameters for the API SDK.
 *
 * @property accountId Account Id provided by Bloomreach
 * @property uuid Android Advertising ID
 * @property visitorType ENUM type for New User or returning user
 * @property domainKey The Bloomreach-provided ID of the domain receiving the request.
 * @property authKey This parameter is only required if you track users via a universal customer ID.
 * @property userId This parameter is only required if you track users via a universal customer ID.
 * @property environment ENUM for api to be pointed to which version., STAGE or PROD
 * @property baseUrl String for base URL
 * @property connectionTimeOut Connection timeout in millis
 * @property maxTotalConnections Max total connections
 * @property responseTimeout Connection timeout for getting response
 */
data class BrApiRequest(
    val accountId: String?,
    val uuid: String?,
    val visitorType: VisitorType?,
    val domainKey: String?,
    var authKey: String? = null,
    var userId: String? = null,
    var environment: Env = Env.STAGE,
    var baseUrl: String? = null,
    var connectionTimeOut: Long = 5000,
    var maxTotalConnections: Int = 10,
    var responseTimeout: Long = 5000
)