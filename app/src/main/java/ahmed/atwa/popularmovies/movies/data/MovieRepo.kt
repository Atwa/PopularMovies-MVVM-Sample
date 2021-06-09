package ahmed.atwa.popularmovies.movies.data

import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import ahmed.atwa.popularmovies.detail.data.TrailerRemote
import ahmed.atwa.popularmovies.detail.data.TrailerResponse
import ahmed.atwa.popularmovies.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun fetchMoviesLocal(): List<MovieEntity>
    suspend fun fetchMoviesRemote(): NetworkResult<MovieResponse>?
    fun syncFavWithDb(movieRemotes: List<MovieRemote>?): List<MovieEntity>?
    suspend fun fetchMovieTrailers(movieId: Int): NetworkResult<TrailerResponse>?
    fun isMovieLiked(id: Int): Boolean
    fun changeLikeState(id: Int, newLikeState: Boolean): Int
}