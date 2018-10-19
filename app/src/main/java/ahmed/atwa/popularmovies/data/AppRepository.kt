

package ahmed.atwa.popularmovies.data

import ahmed.atwa.popularmovies.data.api.AppWebService
import ahmed.atwa.popularmovies.data.api.Movie
import ahmed.atwa.popularmovies.data.api.MovieResponse
import ahmed.atwa.popularmovies.data.api.TrailerResponse
import ahmed.atwa.popularmovies.data.db.AppDatabase
import ahmed.atwa.popularmovies.data.prefrence.AppPrefrence
import ahmed.atwa.popularmovies.utils.AppConstants
import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Singleton
class AppRepository @Inject constructor(private val mContext: Context,
                                        private val mAppDatabase: AppDatabase,
                                        private val mAppPrefrence: AppPrefrence,
                                        private val mAppWebService: AppWebService) {

    fun getMoviesApiCall(): Single<MovieResponse> = mAppWebService.getMostPopular(AppConstants.API_KEY)

    fun getMovieTrailersApiCall(movieId: Int): Single<TrailerResponse> = mAppWebService.getMovieTrailer(movieId, AppConstants.API_KEY)

    fun loadAllMoviesById(movieId: Int): Observable<List<Movie>> = mAppDatabase.loadAllMoviesById(movieId)

    fun isMovieLike(movieId: Int): Observable<Boolean> {
        return Observable.fromCallable { mAppDatabase.movieDao().loadAllMoviesById(movieId).isNotEmpty() }
    }

    fun insertMovieToLikes(movie: Movie): Observable<Boolean> = mAppDatabase.insertMovieToLikes(movie)

    fun removeMovieFromLikes(movie: Movie): Observable<Boolean> = mAppDatabase.removeMovieFromLikes(movie)

}