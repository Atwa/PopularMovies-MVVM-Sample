package ahmed.atwa.popularmovies.movies.data

import ahmed.atwa.popularmovies.detail.data.TrailerResponse
import ahmed.atwa.popularmovies.utils.network.ResultType

interface MovieRepo {
    suspend fun getPopularMovies(page:Int): ResultType<MovieResponse>
    suspend fun fetchMovieTrailers(movieId: Int): ResultType<TrailerResponse>?
    fun isMovieLiked(id: Int): Boolean
    fun changeLikeState(movie: Movie, newLikeState: Boolean)
}