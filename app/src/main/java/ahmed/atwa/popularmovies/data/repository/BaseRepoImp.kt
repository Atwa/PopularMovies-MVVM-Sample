package ahmed.atwa.popularmovies.data.repository

import ahmed.atwa.popularmovies.data.networkConfig.NetworkResult
import ahmed.atwa.popularmovies.data.networkConfig.NoConnectivityException
import android.util.Log
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException


/**
 * Created by Ahmed Atwa on 11/7/2019.
 */
open class BaseRepoImp {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorContext: String): T? {

        val result: NetworkResult<T> = safeApiResult(call)
        var data: T? = null

        when (result) {
            is NetworkResult.Success ->
                data = result.data
            is NetworkResult.Error ->
                Log.e("BaseRepoImp ", " error context = $errorContext & Exception = ${result.error}")
            is NetworkResult.NoConnection ->
                Log.e("BaseRepoImp ", " error context = $errorContext & Exception = ${result.exception}")
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): NetworkResult<T> {
        var result: NetworkResult<T>;
        result = try {
            val response = call.invoke()
            if (response.isSuccessful)
                NetworkResult.Success(response.body()!!)
            else
                NetworkResult.Error(IOException(setErrorMessage(response)))
        } catch (exception: IOException) {
            if (exception is NoConnectivityException)
                NetworkResult.NoConnection(exception)
            else
                NetworkResult.Error(exception)
        }
        return result
    }

    private fun <T : Any> setErrorMessage(response: Response<T>): String {
        val code = response.code().toString()
        val message = try {
            response.errorBody()?.string()?.let {
                val jObjError = JSONObject(it)
                jObjError.getJSONObject("error").getString("message")
            }
        } catch (e: Exception) {
            e.message
        }
        return if (message.isNullOrEmpty()) " error code = $code " else " error code = $code  & error message = $message "
    }


}