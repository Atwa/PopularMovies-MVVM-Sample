package ahmed.atwa.popularmovies.data.remote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
        var results: List<Movie>)

@Entity(tableName = "movies")
data class Movie(
        @Expose
        @SerializedName("vote_count")
        @ColumnInfo(name = "vote_count")
        var vote_count: Int,

        @Expose
        @SerializedName("id")
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Int,

        @Expose
        @SerializedName("video")
        @ColumnInfo(name = "video")
        var video: Boolean,

        @Expose
        @SerializedName("vote_average")
        @ColumnInfo(name = "vote_average")
        val vote_average: Double,

        @Expose
        @SerializedName("title")
        @ColumnInfo(name = "title")
        val title: String?,

        @Expose
        @SerializedName("popularity")
        @ColumnInfo(name = "popularity")
        val popularity: Double,

        @Expose
        @SerializedName("poster_path")
        @ColumnInfo(name = "poster_path")
        val poster_path: String?,

        @Expose
        @SerializedName("original_language")
        @ColumnInfo(name = "original_language")
        val original_language: String?,

        @Expose
        @SerializedName("original_title")
        @ColumnInfo(name = "original_title")
        val original_title: String?,


        @Expose
        @SerializedName("backdrop_path")
        @ColumnInfo(name = "backdrop_path")
        val backdrop_path: String? = "null",

        @Expose
        @SerializedName("adult")
        @ColumnInfo(name = "adult")
        var adult: Boolean = false,

        @Expose
        @SerializedName("overview")
        @ColumnInfo(name = "overview")
        val overview: String?,

        @Expose
        @SerializedName("release_date")
        @ColumnInfo(name = "release_date")
        val release_date: String?,

        @ColumnInfo(name = "is_favourite")
        var isFav: Int? = 0

)