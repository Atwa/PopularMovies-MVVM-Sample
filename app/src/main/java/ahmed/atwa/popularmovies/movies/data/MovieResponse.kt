package ahmed.atwa.popularmovies.movies.data


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ahmed Atwa on 10/19/18.
 */
@Parcelize
data class MovieResponse(
        var page: Int?,
        var total_results: Int?,
        var total_pages: Int,
        var results: List<Movie>) : Parcelable

