package ahmed.atwa.popularmovies.domain.repository

import ahmed.atwa.popularmovies.domain.mapper.MovieEntity
import ahmed.atwa.popularmovies.data.remote.TrailerRemote
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMovies(): Flow<List<MovieEntity>?>
    suspend fun fetchTrailersApiCall(movieId: Int): ArrayList<TrailerRemote>?
    fun isMovieLiked(id: Int): Boolean
    fun changeLikeState(id: Int, newLikeState: Boolean): Int
}