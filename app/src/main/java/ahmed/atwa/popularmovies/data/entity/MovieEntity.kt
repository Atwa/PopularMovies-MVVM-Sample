package ahmed.atwa.popularmovies.data.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieEntity(

        var id: Int,

        var isFav: Int? = 0,

        var poster_path: String?,

        var popularity: Double,

        var vote_count: Int,

        var video: Boolean,

        var vote_average: Double,

        var title: String?,

        var original_language: String?,

        var original_title: String?,

        var backdrop_path: String? = "null",

        var adult: Boolean = false,

        var overview: String?,

        var release_date: String?
)