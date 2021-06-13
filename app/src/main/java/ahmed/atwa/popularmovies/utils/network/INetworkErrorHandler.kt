package ahmed.atwa.popularmovies.utils.network

import retrofit2.Response
import kotlin.Exception

interface INetworkErrorHandler {
    fun <T : Any> resolveErrorMessage(response: Response<T>): NetworkResult.Error
}