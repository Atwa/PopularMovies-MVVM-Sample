package ahmed.atwa.popularmovies.data.repository

import ahmed.atwa.popularmovies.data.model.Movie
import ahmed.atwa.popularmovies.data.model.Trailer
import ahmed.atwa.popularmovies.data.source.MovieApi
import ahmed.atwa.popularmovies.data.source.MovieDao
import ahmed.atwa.popularmovies.data.source.TrailerApi
import ahmed.atwa.popularmovies.utils.AppConstants
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/17/2019.
 */

@Singleton
class MovieRepository @Inject constructor(
        private val movieDao: MovieDao,
        private val movieApi: MovieApi,
        private val trailerApi: TrailerApi) {


    fun isMovieLiked(movieId: Int): Boolean {
        val result = movieDao.isMovieLiked(movieId)
        return if (result.isNotEmpty() && result[0] != null) result[0] == 1 else false
    }

    fun changeMovieLikeState(movieId: Int, state: Boolean) = if (state) movieDao.setMovieLiked(movieId) else movieDao.setMovieUnLiked(movieId)

    fun fetchMoviesLocal(): List<Movie> = movieDao.fetchAllMovies()

    fun syncFavWithDb(movies: List<Movie>): ArrayList<Movie> {
        val updatedList = ArrayList<Movie>()
        movies.forEach { movie -> movie.isFav = if (isMovieLiked(movie.id)) 1 else 0; updatedList.add(movie) }
        movieDao.insertAll(updatedList)
        return updatedList
    }

    suspend fun fetchMoviesApiCall(): ArrayList<Movie> {
        val result: ArrayList<Movie> = ArrayList()
        try {
            val response = movieApi.getMostPopular(AppConstants.API_KEY)
            if (response.isSuccessful && response.body() != null) {
                result.addAll(response.body()!!.results)
            } else {
                Log.e(AppConstants.DEBUG_TAG, response.errorBody()?.string()!!)
            }
        } catch (e: Throwable) {
            Log.e(AppConstants.DEBUG_TAG, e.toString())
        }
        return result
    }

    suspend fun fetchTrailersApiCall(movieId: Int): ArrayList<Trailer> {
        val result: ArrayList<Trailer> = ArrayList()
        try {
            val response = trailerApi.getMovieTrailer(movieId, AppConstants.API_KEY)
            if (response.isSuccessful && response.body() != null) {
                result.addAll(response.body()!!.results)
            } else
                Log.e(AppConstants.DEBUG_TAG, response.errorBody()?.string()!!)
        } catch (e: Throwable) {
            Log.e(AppConstants.DEBUG_TAG, e.toString())
        }
        return result
    }


}