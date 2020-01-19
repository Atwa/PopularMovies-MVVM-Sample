package ahmed.atwa.popularmovies.domain.mapper

import ahmed.atwa.popularmovies.data.local.MovieLocal
import ahmed.atwa.popularmovies.data.remote.MovieRemote
import java.util.*

interface MovieMapper {
     fun mapFromLocal(from: MovieLocal): MovieEntity
     fun mapFromRemoteToLocal(from: MovieRemote, isFav: Int): MovieLocal
}