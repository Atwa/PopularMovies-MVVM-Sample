package ahmed.atwa.popularmovies.movies.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieRemote(
        var vote_count: Int,
        var id: Int,
        var video: Boolean,
        val vote_average: Double,
        val title: String?,
        val popularity: Double,
        val poster_path: String?,
        val original_language: String?,
        val original_title: String?,
        val backdrop_path: String? = "null",
        var adult: Boolean = false,
        val overview: String?,
        val release_date: String?
) : Parcelable