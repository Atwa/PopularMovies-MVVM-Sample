package ahmed.atwa.popularmovies.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieLocal(@PrimaryKey
                      @ColumnInfo(name = "id")
                      var id: Int,

                      @ColumnInfo(name = "is_favourite")
                      var isFav: Int? = 0,

                      @ColumnInfo(name = "poster_path")
                      val poster_path: String?,

                      @ColumnInfo(name = "popularity")
                      val popularity: Double,

                      @ColumnInfo(name = "vote_count")
                      var vote_count: Int,

                      @ColumnInfo(name = "video")
                      var video: Boolean,

                      @ColumnInfo(name = "vote_average")
                      val vote_average: Double,

                      @ColumnInfo(name = "title")
                      val title: String?,

                      @ColumnInfo(name = "original_language")
                      val original_language: String?,

                      @ColumnInfo(name = "original_title")
                      val original_title: String?,

                      @ColumnInfo(name = "backdrop_path")
                      val backdrop_path: String? = "null",

                      @ColumnInfo(name = "adult")
                      var adult: Boolean = false,

                      @ColumnInfo(name = "overview")
                      val overview: String?,

                      @ColumnInfo(name = "release_date")
                      val release_date: String?
)