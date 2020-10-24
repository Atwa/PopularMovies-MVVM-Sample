package ahmed.atwa.popularmovies.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ahmed Atwa on 10/19/18.
 */
@Parcelize
data class TrailerResponse(
        var id: Int,
        var results: List<TrailerRemote>
) : Parcelable

@Parcelize
data class TrailerRemote(
        val id: String,
        val iso_639_1: String,
        val iso_3166_1: String,
        val key: String,
        val name: String,
        val site: String,
        var size: Int,
        val type: String
) : Parcelable
