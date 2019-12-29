package ahmed.atwa.popularmovies.data.repo

import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.data.remote.model.Trailer
import ahmed.atwa.popularmovies.data.local.MovieDao
import ahmed.atwa.popularmovies.data.remote.MovieApi
import ahmed.atwa.popularmovies.data.remote.TrailerApi
import ahmed.atwa.popularmovies.ui.base.BaseRepository
import ahmed.atwa.popularmovies.utils.AppConstants
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/17/2019.
 */

@Singleton
class MovieRepoImp @Inject constructor(
        private val movieDao: MovieDao,
        private val movieApi: MovieApi,
        private val trailerApi: TrailerApi) : BaseRepository() ,MovieRepo {

    override suspend fun getMovies(): ArrayList<Movie> {
        val localData = fetchMoviesLocal()
        val remoteData = fetchMoviesRemote()
        val syncedData = storeMoviesLocal(remoteData)
        return localData then syncedData
    }

    private fun fetchMoviesLocal(): ArrayList<Movie> = movieDao.fetchAllMovies()

    private suspend fun fetchMoviesRemote(): ArrayList<Movie>? {
        val data = safeApiCall({ movieApi.getMostPopular(AppConstants.API_KEY) },
                "fetching movies")
        return if (data != null) data.results as ArrayList<Movie> else null
    }

    private fun storeMoviesLocal(results: List<Movie>?): ArrayList<Movie>? {
        return if (!results.isNullOrEmpty()) syncFavWithDb(results) else null
    }

    private fun syncFavWithDb(movies: List<Movie>): ArrayList<Movie> {
        val tempList = ArrayList<Movie>()
        movies.forEach { movie -> movie.isFav = if (isMovieLiked(movie.id)) 1 else 0; tempList.add(movie) }
        movieDao.insertAll(tempList)
        return tempList
    }


    fun isMovieLiked(movieId: Int): Boolean {
        val result = movieDao.isMovieLiked(movieId)
        return if (result.isNotEmpty() && result[0] != null) result[0] == 1 else false
    }

    fun changeMovieLikeState(movieId: Int, state: Boolean) = if (state) movieDao.setMovieLiked(movieId) else movieDao.setMovieUnLiked(movieId)


    override suspend fun fetchTrailersApiCall(movieId: Int): ArrayList<Trailer>? {
        val data = safeApiCall({ trailerApi.getMovieTrailer(movieId, AppConstants.API_KEY) },
                "Error fetching Trailers")
        return if (data != null) data.results as ArrayList<Trailer> else null

    }


}