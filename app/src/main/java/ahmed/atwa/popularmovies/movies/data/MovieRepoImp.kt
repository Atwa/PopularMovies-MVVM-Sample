package ahmed.atwa.popularmovies.movies.data

import ahmed.atwa.popularmovies.detail.data.TrailerApi
import ahmed.atwa.popularmovies.detail.data.TrailerResponse
import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import ahmed.atwa.popularmovies.movies.domain.MovieMapper
import ahmed.atwa.popularmovies.utils.commons.AppConstants
import ahmed.atwa.popularmovies.utils.database.MovieDao
import ahmed.atwa.popularmovies.utils.network.NetworkResult
import ahmed.atwa.popularmovies.utils.network.NetworkRouter
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/17/2019.
 */

@Singleton
class MovieRepoImp @Inject constructor(
        private val movieDao: MovieDao,
        private val movieApi: MovieApi,
        private val trailerApi: TrailerApi,
        private val movieMapper: MovieMapper) : MovieRepo {

    override suspend fun fetchMoviesRemote(): NetworkResult<List<MovieEntity>> {
        val result = NetworkRouter.invokeCall { movieApi.getMostPopular(AppConstants.API_KEY) }
        return when (result) {
            is NetworkResult.Success -> result.data.results.map { movieMapper.mapFromRemoteToEntity(it) }
                    .toList().run { NetworkResult.Success(this) }
            is NetworkResult.Error -> NetworkResult.Error(result.error)
        }
    }

    override suspend fun fetchMovieTrailers(movieId: Int): NetworkResult<TrailerResponse>? {
        return NetworkRouter.invokeCall { trailerApi.getMovieTrailer(movieId, AppConstants.API_KEY) }
    }

    override fun isMovieLiked(id: Int): Boolean {
        return movieDao.fetchFavouriteMovies().contains(id)
    }

    override fun changeLikeState(movie: MovieEntity, newLikeState: Boolean) {
        movieMapper.mapFromEntityToLocal(movie).run {
            if (newLikeState) movieDao.insertMovie(this)
            else movieDao.removeMovie(this)
        }
    }

}