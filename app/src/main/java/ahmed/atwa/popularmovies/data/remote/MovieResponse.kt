package ahmed.atwa.popularmovies.data.remote


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

data class MovieResponse(

        @Expose
        @SerializedName("page")
        var page: Integer,
        @Expose
        @SerializedName("total_results")
        var total_results: Integer,
        @Expose
        @SerializedName("total_pages")
        var total_pages: Integer,
        @Expose
        @SerializedName("results")
        var results: List<MovieRemote>)

data class MovieRemote(
        @Expose
        @SerializedName("vote_count")
        var vote_count: Int,

        @Expose
        @SerializedName("id")
        var id: Int,

        @Expose
        @SerializedName("video")
        var video: Boolean,

        @Expose
        @SerializedName("vote_average")
        val vote_average: Double,

        @Expose
        @SerializedName("title")
        val title: String?,

        @Expose
        @SerializedName("popularity")
        val popularity: Double,

        @Expose
        @SerializedName("poster_path")
        val poster_path: String?,

        @Expose
        @SerializedName("original_language")
        val original_language: String?,

        @Expose
        @SerializedName("original_title")
        val original_title: String?,


        @Expose
        @SerializedName("backdrop_path")
        val backdrop_path: String? = "null",

        @Expose
        @SerializedName("adult")
        var adult: Boolean = false,

        @Expose
        @SerializedName("overview")
        val overview: String?,

        @Expose
        @SerializedName("release_date")
        val release_date: String?

)