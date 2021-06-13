package ahmed.atwa.popularmovies.utils.network

import retrofit2.Response
import java.io.IOException


/**
 * Created by Ahmed Atwa on 11/7/2019.
 */
object NetworkRouter {

    private val networkErrorHandler: INetworkErrorHandler = NetworkErrorHandler()

    suspend fun <T : Any> invokeCall(call: suspend () -> Response<T>): ResultType<T> {
        return try {
            val response = call.invoke()
            if (isSuccessResponse(response)) ResultType.Success(response.body()!!)
            else networkErrorHandler.resolveErrorMessage(response)
        } catch (exception: IOException) {
            if (exception is NoConnectionException) ResultType.Error(exception)
            else ResultType.Error(exception)
        }
    }

    private fun <T> isSuccessResponse(response: Response<T>?): Boolean {
        return response != null && response.isSuccessful && response.body() != null
    }


}