package ahmed.atwa.popularmovies.data.repository

import ahmed.atwa.popularmovies.data.entity.MovieEntity
import ahmed.atwa.popularmovies.data.entity.MovieMapper
import ahmed.atwa.popularmovies.data.local.MovieLocal
import ahmed.atwa.popularmovies.data.remote.Movie
import ahmed.atwa.popularmovies.data.remote.Trailer
import ahmed.atwa.popularmovies.data.local.dataSource.MovieDao
import ahmed.atwa.popularmovies.data.remote.dataSource.MovieApi
import ahmed.atwa.popularmovies.data.remote.dataSource.TrailerApi
import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import ahmed.atwa.popularmovies.presentation.commons.AppConstants
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
        private val trailerApi: TrailerApi,
        private val movieMapper: MovieMapper) : BaseRepoImp(), MovieRepo {


/*    override fun getMovies(): Flow<List<MovieEntity>?> = flow {
        val tempMovies: ArrayList<MovieEntity> = ArrayList()
        fetchMoviesLocal().forEach { movieMapper.mapFromLocal(it).also { tempMovies.add(it) } }
        emit(tempMovies)
        tempMovies.clear()


        fetchMoviesRemote().forEach { movieMapper.mapFromRemote(it).also { movies.add(it) } }
        movies = storeMoviesLocal(movies)
        emit(movies)
    }*/


    override fun getMovies(): Flow<List<MovieEntity>?> = flow {
        val localData = fetchMoviesLocal().apply { movieMapper.mapFromLocal(it) }
        emit(localData)


        var remoteData: ArrayList<MovieLocal> = ArrayList()
        fetchMoviesRemote()?.forEach { movieMapper.mapFromRemoteToLocal(it).also { remoteData.add(it) } }
        remoteData = storeMoviesLocal(remoteData).forEach{movieMapper.mapFromLocal(it)}

     //   emit(remoteData)


    }


    private fun fetchMoviesLocal(): List<MovieLocal> = movieDao.fetchAllMovies()

    private suspend fun fetchMoviesRemote(): ArrayList<Movie>? {
        val data = safeApiCall({ movieApi.getMostPopular(AppConstants.API_KEY) }, "fetching movies")
        return if (data != null) data.results as ArrayList<Movie> else null
    }

    private fun storeMoviesLocal(results: List<MovieLocal>?): ArrayList<MovieLocal> {
        return if (!results.isNullOrEmpty()) syncFavWithDb(results) else null
    }

    private fun syncFavWithDb(movies: List<MovieLocal>): ArrayList<MovieLocal> {
        val tempList = ArrayList<MovieLocal>()
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