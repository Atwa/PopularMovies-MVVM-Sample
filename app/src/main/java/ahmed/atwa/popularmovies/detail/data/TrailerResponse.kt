package ahmed.atwa.popularmovies.detail.data

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
