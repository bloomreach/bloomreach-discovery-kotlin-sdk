package network

import com.fasterxml.jackson.databind.ObjectMapper
import model.BrApiError
import model.rp.RpError
import model.visualsearch.ImageUploadResponse
import org.slf4j.LoggerFactory
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 *  Class to perform File Upload API call
 */
internal class FileUpload(url: URL, authKey: String) {
    companion object {
        private val LINE_FEED = "\r\n"
        private val maxBufferSize = 1024 * 1024
        private val charset = "UTF-8"
    }
    // creates a unique boundary based on time stamp
    private val boundary: String = "===" + System.currentTimeMillis() + "==="
    private val httpConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
    private val outputStream: OutputStream
    private val writer: PrintWriter

    private val logger = LoggerFactory.getLogger(BrHttpClient::class.java.name)

    init {
        httpConnection.setRequestProperty("Accept-Charset", "UTF-8")
        httpConnection.setRequestProperty("Connection", "Keep-Alive")
        httpConnection.setRequestProperty("Cache-Control", "no-cache")
        httpConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary)
        httpConnection.setRequestProperty("auth_key", authKey)
        //TODO check
//        httpConnection.setRequestProperty(
//            "User-Agent",
//            "Bloomreach/${BuildConfig.SDK_VERSION} " + System.getProperty("http.agent")
//        )
        httpConnection.setChunkedStreamingMode(maxBufferSize)
        httpConnection.doInput = true
        httpConnection.doOutput = true    // indicates POST method
        httpConnection.useCaches = false
        outputStream = httpConnection.outputStream
        writer = PrintWriter(OutputStreamWriter(outputStream, charset), true)
    }

    /**
     * Method to add file part to the API call
     * @param inputStream inputStream of the image
     * @param fileName file name of the image
     */
    @Throws(IOException::class)
    fun addFilePart(inputStream: InputStream, fileName: String) {
        logger.trace("addFilePart() - fileName: $fileName")

        writer.append("--").append(boundary).append(LINE_FEED)
        writer.append("Content-Disposition: form-data; name=\"").append("image")
            .append("\"; filename=\"").append(fileName).append("\"").append(LINE_FEED)
        writer.append("Content-Type: ").append("image/jpeg").append(LINE_FEED)
        writer.append(LINE_FEED)
        writer.flush()

        inputStream.copyTo(outputStream, maxBufferSize)

        outputStream.flush()
        inputStream.close()
        writer.append(LINE_FEED)
        writer.flush()
    }

    /**
     * Method to perform File Upload API
     * @return Any response object CoreResponse if API call success else return BrApiError object
     */
    @Throws(IOException::class)
    fun upload() : Any {
        logger.trace("upload()")

        writer.append(LINE_FEED).flush()
        writer.append("--").append(boundary).append("--")
            .append(LINE_FEED)
        writer.close()

        try {
            // checks server's status code first
            val responseCode = httpConnection.responseCode
            logger.info("API response code: $responseCode ")
           // Log.i("Visual Search upload API CALL:", "responseCode: $responseCode")
            if (responseCode in 200..299)  {
                val reader = BufferedReader(InputStreamReader(httpConnection
                    .inputStream))
                val response = reader.use(BufferedReader::readText)
                logger.trace("Response: $response")
                httpConnection.disconnect()

                val responseMapper = ObjectMapper()
                return responseMapper.readValue(response, ImageUploadResponse::class.java)
            } else {
                if (httpConnection.errorStream != null) {
                    val result = httpConnection.errorStream.bufferedReader().use(BufferedReader::readText)
                    logger.trace("errorStream: $result")

                    httpConnection.errorStream.close()
                    //covert error result to BrApiError object
                    val responseMapper = ObjectMapper()
                    val rpError = responseMapper.readValue(result, RpError::class.java)
                    return BrApiError(rpError.detail ?: "The server responded with an error while uploading the image", responseCode)
                }
                logger.error("Error code: ${responseCode}, Error Message: ${httpConnection.responseCode}")
                return BrApiError("The server responded with an error while uploading the image", responseCode)
            }

        } catch (exception: Exception) {
            throw exception
        }
    }
}
