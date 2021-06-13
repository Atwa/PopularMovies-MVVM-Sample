package ahmed.atwa.popularmovies.movies.data

import ahmed.atwa.popularmovies.BuildConfig
import ahmed.atwa.popularmovies.detail.data.TrailerApi
import ahmed.atwa.popularmovies.detail.data.TrailerResponse
import ahmed.atwa.popularmovies.utils.database.MovieDao
import ahmed.atwa.popularmovies.utils.network.NetworkRouter
import ahmed.atwa.popularmovies.utils.network.ResultType
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/17/2019.
 */

@Singleton
class MovieRepoImp @Inject constructor(
        private val movieDao: MovieDao,
        private val movieApi: MovieApi,
        private val trailerApi: TrailerApi) : MovieRepo {

    override suspend fun getPopularMovies(page: Int): ResultType<MovieResponse> {
        return NetworkRouter.invokeCall { movieApi.getMostPopular(BuildConfig.API_KEY, page) }
    }

    override suspend fun fetchMovieTrailers(movieId: Int): ResultType<TrailerResponse> {
        return NetworkRouter.invokeCall { trailerApi.getMovieTrailer(movieId, BuildConfig.API_KEY) }
    }

    override fun isMovieLiked(id: Int): Boolean {
        return movieDao.fetchFavouriteMovies().contains(id)
    }

    override fun changeLikeState(movie: Movie, newLikeState: Boolean) {
        if (newLikeState) movieDao.insertMovie(movie)
        else movieDao.removeMovie(movie)
    }

}