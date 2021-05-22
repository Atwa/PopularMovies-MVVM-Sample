package ahmed.atwa.popularmovies.detail.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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