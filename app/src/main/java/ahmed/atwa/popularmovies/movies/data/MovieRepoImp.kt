package ahmed.atwa.popularmovies.movies.data

import ahmed.atwa.popularmovies.detail.data.TrailerApi
import ahmed.atwa.popularmovies.detail.data.TrailerRemote
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

    override fun fetchMoviesLocal(): List<MovieEntity> {
        return movieDao.fetchAllMovies().map { movieMapper.mapFromLocalToEntity(it) }.toList()
    }

    override suspend fun fetchMoviesRemote(): NetworkResult<MovieResponse>? {
        return NetworkRouter.invokeCall { movieApi.getMostPopular(AppConstants.API_KEY) }
    }

    override fun syncFavWithDb(movieRemotes: List<MovieRemote>?): List<MovieEntity>? {
        return movieRemotes?.map { movieMapper.mapFromRemoteToLocal(it, getLikeState(it.id)) }?.toList()
                ?.also { movieDao.insertAll(it) }
                ?.run { this.map { local -> movieMapper.mapFromLocalToEntity(local) }.toList() }
    }

    override suspend fun fetchMovieTrailers(movieId: Int): NetworkResult<TrailerResponse>? {
        return NetworkRouter.invokeCall { trailerApi.getMovieTrailer(movieId, AppConstants.API_KEY) }
    }

    override fun isMovieLiked(id: Int): Boolean {
        val result = movieDao.isMovieLiked(id)
        return if (result.isNotEmpty() && result[0] != null) result[0] == 1 else false
    }

    private fun getLikeState(id: Int): Int {
        return if (isMovieLiked(id)) 1 else 0
    }

    override fun changeLikeState(id: Int, newLikeState: Boolean) = if (newLikeState) movieDao.setMovieLiked(id) else movieDao.setMovieUnLiked(id)

}