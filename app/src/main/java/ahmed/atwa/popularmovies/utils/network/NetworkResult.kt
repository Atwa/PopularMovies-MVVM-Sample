package ahmed.atwa.popularmovies.utils.network

/**
 * Created by Ahmed Atwa on 11/7/2019.
 */
sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val error: Exception) : NetworkResult<Nothing>()
}