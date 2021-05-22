package ahmed.atwa.popularmovies.movies.data

import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import ahmed.atwa.popularmovies.detail.data.TrailerRemote
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMovies(): Flow<List<MovieEntity>?>
    suspend fun fetchMovieTrailers(movieId: Int): ArrayList<TrailerRemote>?
    fun isMovieLiked(id: Int): Boolean
    fun changeLikeState(id: Int, newLikeState: Boolean): Int
}