package ahmed.atwa.popularmovies.domain.repository

import ahmed.atwa.popularmovies.data.entity.MovieEntity
import ahmed.atwa.popularmovies.data.remote.Movie
import ahmed.atwa.popularmovies.data.remote.Trailer
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMovies(): Flow<List<MovieEntity>?>
    suspend fun fetchTrailersApiCall(movieId: Int): ArrayList<Trailer>?
    fun getLikeState(id: Int): Boolean
    fun changeLikeState(id: Int, setLiked: Boolean): Int
}