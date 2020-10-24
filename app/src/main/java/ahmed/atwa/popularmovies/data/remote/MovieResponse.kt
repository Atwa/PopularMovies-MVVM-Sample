package ahmed.atwa.popularmovies.data.remote


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ahmed Atwa on 10/19/18.
 */
@Parcelize
data class MovieResponse(
        var page: Int,
        var total_results: Int,
        var total_pages: Int,
        var results: List<MovieRemote>) : Parcelable

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