package ahmed.atwa.popularmovies.movies.data

import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import ahmed.atwa.popularmovies.detail.data.TrailerResponse
import ahmed.atwa.popularmovies.utils.network.NetworkResult

interface MovieRepo {
    suspend fun fetchMoviesRemote(): NetworkResult<List<MovieEntity>>
    suspend fun fetchMovieTrailers(movieId: Int): NetworkResult<TrailerResponse>?
    fun isMovieLiked(id: Int): Boolean
    fun changeLikeState(movie: MovieEntity, newLikeState: Boolean)
}