package ahmed.atwa.popularmovies.data.repo

import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.data.remote.model.Trailer
import ahmed.atwa.popularmovies.data.local.MovieDao
import ahmed.atwa.popularmovies.data.remote.MovieApi
import ahmed.atwa.popularmovies.data.remote.TrailerApi
import ahmed.atwa.popularmovies.ui.base.BaseRepository
import ahmed.atwa.popularmovies.utils.AppConstants
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/17/2019.
 */

@Singleton
class MovieRepoImp @Inject constructor(
        private val movieDao: MovieDao,
        private val movieApi: MovieApi,
        private val trailerApi: TrailerApi) : BaseRepository(), MovieRepo {


    override fun getMovies(): Flow<List<Movie>?> = flow {
        emit(fetchMoviesLocal())
        val syncedData = storeMoviesLocal(fetchMoviesRemote())
        emit(syncedData)
    }

    private fun fetchMoviesLocal(): List<Movie> = movieDao.fetchAllMovies()

    private suspend fun fetchMoviesRemote(): ArrayList<Movie>? {
        val data = safeApiCall({ movieApi.getMostPopular(AppConstants.API_KEY) }, "fetching movies")
        return if (data != null) data.results as ArrayList<Movie> else null
    }

    private fun storeMoviesLocal(results: List<Movie>?): ArrayList<Movie>? {
        return if (!results.isNullOrEmpty()) syncFavWithDb(results) else null
    }

    private fun syncFavWithDb(movies: List<Movie>): ArrayList<Movie> {
        val tempList = ArrayList<Movie>()
        movies.forEach { movie -> movie.isFav = if (getLikeState(movie.id)) 1 else 0; tempList.add(movie) }
        movieDao.insertAll(tempList)
        return tempList
    }

    override suspend fun fetchTrailersApiCall(movieId: Int): ArrayList<Trailer>? {
        val data = safeApiCall({ trailerApi.getMovieTrailer(movieId, AppConstants.API_KEY) }, "Error fetching Trailers")
        return if (data != null) data.results as ArrayList<Trailer> else null

    }

    override fun getLikeState(id: Int): Boolean {
        val result = movieDao.isMovieLiked(id)
        return if (result.isNotEmpty() && result[0] != null) result[0] == 1 else false
    }

    override fun changeLikeState(id: Int, setLiked: Boolean) = if (setLiked) movieDao.setMovieLiked(id) else movieDao.setMovieUnLiked(id)

}