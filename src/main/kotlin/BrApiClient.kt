import enums.Env
import enums.VisitorType
import network.BrHttpClient
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.apache.hc.core5.util.Timeout
import java.util.concurrent.TimeUnit

/**
 * Abstract Class for building different type of API objects with some common parameters.
 */
abstract class BrApiClient(private val brApiRequest: BrApiRequest) {

    internal var brHttpClient: BrHttpClient

    init {
        //Common Pooling manager for API calls
        val poolingConnManager = PoolingHttpClientConnectionManager()
        poolingConnManager.maxTotal = brApiRequest.maxTotalConnections

        //          maxTotal – Set the maximum number of total open connections
        poolingConnManager.maxTotal = brApiRequest.maxTotalConnections

//          Timeout
        val timeout = Timeout.of(brApiRequest.connectionTimeOut, TimeUnit.MILLISECONDS)

        val config: RequestConfig = RequestConfig.custom()
            .setConnectTimeout(timeout)
            .setConnectionRequestTimeout(timeout)
            .setResponseTimeout(brApiRequest.responseTimeout, TimeUnit.MILLISECONDS)
            .build()

//          set pooling and config paramters to client
        val client = HttpClients.custom()
            .setConnectionManager(poolingConnManager)
            .setDefaultRequestConfig(config)
            .build()

        brHttpClient = BrHttpClient(client)
    }

    abstract class Builder<T>(
        var accountId: String? = null,
        var uuid: String? = null,
        var visitorType: VisitorType? = null,
        var domainKey: String? = null,
        var authKey: String? = null,
        var userId: String? = null,
        var environment: Env = Env.STAGE,
        var baseUrl: String? = null,
        var connectionTimeOut: Long = 5000,
        var maxTotalConnections: Int = 10,
        var responseTimeout: Long = 5000
    ) {
        fun accountId(accountId: String) = apply { this.accountId = accountId }
        fun uuid(uuid: String) = apply { this.uuid = uuid }
        fun visitorType(visitorType: VisitorType) = apply { this.visitorType = visitorType }
        fun domainKey(domainKey: String) = apply { this.domainKey = domainKey }
        fun authKey(authKey: String) = apply { this.authKey = authKey }
        fun userId(userId: String) = apply { this.userId = userId }
        fun environment(environment: Env) = apply { this.environment = environment }
        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun connectionTimeOut(connectionTimeOut: Long) = apply { this.connectionTimeOut = connectionTimeOut }
        fun maxTotalConnections(maxTotalConnections: Int) = apply { this.maxTotalConnections = maxTotalConnections }
        fun responseTimeout(responseTimeout: Long) = apply { this.responseTimeout = responseTimeout }
        abstract fun build(): T?
    }
}