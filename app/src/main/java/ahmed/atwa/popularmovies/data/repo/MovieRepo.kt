package ahmed.atwa.popularmovies.data.repo

import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.data.remote.model.Trailer

interface MovieRepo {
    suspend fun getMovies(): ArrayList<Movie>
    suspend fun fetchTrailersApiCall(movieId: Int): ArrayList<Trailer>?
}