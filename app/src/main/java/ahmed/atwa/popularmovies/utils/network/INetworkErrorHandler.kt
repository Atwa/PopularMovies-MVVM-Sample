package ahmed.atwa.popularmovies.utils.network

import okhttp3.ResponseBody
import retrofit2.Response

interface INetworkErrorHandler {
    fun <T : Any> resolveErrorMessage(response: Response<T>): NetworkResult.Error
}