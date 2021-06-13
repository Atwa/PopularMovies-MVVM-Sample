package ahmed.atwa.popularmovies.utils.network

import retrofit2.Response

interface INetworkErrorHandler {
    fun <T : Any> resolveErrorMessage(response: Response<T>): ResultType.Error
}