package ahmed.atwa.popularmovies.data.repo

import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.data.remote.model.Trailer
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMovies(): Flow<List<Movie>?>
    suspend fun fetchTrailersApiCall(movieId: Int): ArrayList<Trailer>?
    fun getLikeState(id: Int): Boolean
    fun changeLikeState(id: Int, setLiked: Boolean): Int
}