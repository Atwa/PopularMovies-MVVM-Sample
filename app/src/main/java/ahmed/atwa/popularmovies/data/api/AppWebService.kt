package ahmed.atwa.popularmovies.data.api

import android.database.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import javax.inject.Singleton
import retrofit2.http.GET
import kotlin.collections.ArrayList


@Singleton
interface AppWebService {

    companion object {
        const val POPULAR_MOVIES_QUERY: String = ("discover/movie?sort_by=popularity.desc")
        const val API_KEY: String = "api_key"
    }



    @GET(POPULAR_MOVIES_QUERY)
    fun getMostPopular(@Query(API_KEY) apiKey: String) : Single<MovieResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(@Path("movie_id") id: Int, @Query(API_KEY) apiKey: String): Single<TrailerResponse>


}