package ahmed.atwa.popularmovies.data.api

import android.database.Observable
import dagger.Provides
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.inject.Singleton
import retrofit2.http.GET
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Singleton
interface AppWebService {


    companion object {
        const val BASE_URL: String = ("https://api.themoviedb.org/3/")
        const val API_KEY: String = "api_key"
        const val POPULAR_MOVIES_QUERY: String = ("discover/movie?sort_by=popularity.desc")
    }



    @GET(POPULAR_MOVIES_QUERY)
     fun getMostPopular(@Query(API_KEY) apiKey: String): Single<MovieResponse>

    @GET("movie/{movie_id}/videos")
     fun getMovieTrailer(@Path("movie_id") id: Int, @Query(API_KEY) apiKey: String): Single<TrailerResponse>


}