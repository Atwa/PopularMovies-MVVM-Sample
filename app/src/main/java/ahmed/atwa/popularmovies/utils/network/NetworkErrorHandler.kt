package ahmed.atwa.popularmovies.utils.network

import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

class NetworkErrorHandler : INetworkErrorHandler {

    override fun <T : Any> resolveErrorMessage(response: Response<T>): ResultType.Error {
        val code = response.code().toString()
        val message = try {
            response.errorBody()?.string()?.let {
                val jObjError = JSONObject(it)
                jObjError.getJSONObject("error").getString("message")
            }
        } catch (e: Exception) {
            e.message
        }
        val errorMessage = if (message.isNullOrEmpty()) " error code = $code "
        else " error code = $code  & error message = $message "
        return ResultType.Error(IOException(errorMessage))
    }
}