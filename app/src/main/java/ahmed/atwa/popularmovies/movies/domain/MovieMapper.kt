package ahmed.atwa.popularmovies.movies.domain

import ahmed.atwa.popularmovies.movies.data.MovieLocal
import ahmed.atwa.popularmovies.movies.data.MovieRemote

interface MovieMapper {
     fun mapFromLocalToEntity(from: MovieLocal): MovieEntity
     fun mapFromRemoteToLocal(from: MovieRemote, isFav: Int): MovieLocal
}