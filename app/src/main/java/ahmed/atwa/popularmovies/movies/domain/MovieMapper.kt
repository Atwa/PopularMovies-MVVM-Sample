package ahmed.atwa.popularmovies.movies.domain

import ahmed.atwa.popularmovies.movies.data.MovieLocal
import ahmed.atwa.popularmovies.movies.data.MovieRemote

interface MovieMapper {
     fun mapFromRemoteToEntity(from: MovieRemote): MovieEntity
     fun mapFromEntityToLocal(from: MovieEntity): MovieLocal
}