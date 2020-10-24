package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.local.MovieLocal
import ahmed.atwa.popularmovies.data.remote.MovieRemote
import ahmed.atwa.popularmovies.domain.MovieEntity

interface MovieMapper {
     fun mapFromLocal(from: MovieLocal): MovieEntity
     fun mapFromRemoteToLocal(from: MovieRemote, isFav: Int): MovieLocal
}