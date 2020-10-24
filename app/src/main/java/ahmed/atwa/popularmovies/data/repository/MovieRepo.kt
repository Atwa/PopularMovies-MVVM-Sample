package ahmed.atwa.popularmovies.data.repository

import ahmed.atwa.popularmovies.domain.MovieEntity
import ahmed.atwa.popularmovies.data.remote.TrailerRemote
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMovies(): Flow<List<MovieEntity>?>
    suspend fun fetchMovieTrailers(movieId: Int): ArrayList<TrailerRemote>?
    fun isMovieLiked(id: Int): Boolean
    fun changeLikeState(id: Int, newLikeState: Boolean): Int
}