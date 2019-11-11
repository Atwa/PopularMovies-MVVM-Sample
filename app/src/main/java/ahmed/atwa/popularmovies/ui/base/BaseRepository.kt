package ahmed.atwa.popularmovies.ui.base

import ahmed.atwa.popularmovies.data.network.NetworkResult
import ahmed.atwa.popularmovies.data.network.NoConnectivityException
import android.util.Log
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException


/**
 * Created by Ahmed Atwa on 11/7/2019.
 */
open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorContext: String): T? {

        val result: NetworkResult<T> = safeApiResult(call)
        var data: T? = null

        when (result) {
            is NetworkResult.Success ->
                data = result.data
            is NetworkResult.Error ->
                Log.e("BaseRepository ", " error context = $errorContext & Exception = ${result.error}")
            is NetworkResult.NoConnection ->
                Log.e("BaseRepository ", " error context = $errorContext & Exception = ${result.exception}")
        }


        return data

    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) return NetworkResult.Success(response.body()!!)
            return NetworkResult.Error(IOException(setErrorMessage(response)))
        } catch (exception: IOException) {
            if (exception is NoConnectivityException) return NetworkResult.NoConnection(exception)
            return NetworkResult.Error(exception)
        }

    }

    private fun <T : Any> setErrorMessage(response: Response<T>): String {
        val code = response.code().toString()
        val message = try {
            val jObjError = JSONObject(response.errorBody()?.string())
            jObjError.getJSONObject("error").getString("message")
        } catch (e: Exception) {
            e.message
        }
        return if (message.isNullOrEmpty()) " error code = $code " else " error code = $code  & error message = $message "
    }


}