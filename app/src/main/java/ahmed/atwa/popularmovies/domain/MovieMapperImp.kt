package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.local.MovieLocal
import ahmed.atwa.popularmovies.data.remote.MovieRemote

class MovieMapperImp : MovieMapper {


    override fun mapFromLocal(from: MovieLocal): MovieEntity {
        return MovieEntity(
                from.id,
                from.isFav == 1,
                from.poster_path, from.popularity,
                from.vote_count,
                from.video,
                from.vote_average,
                from.title,
                from.backdrop_path,
                from.overview,
                from.release_date
        )
    }

    override fun mapFromRemoteToLocal(from: MovieRemote, isFav: Int): MovieLocal {
        return MovieLocal(
                from.id,
                isFav,
                from.poster_path,
                from.popularity,
                from.vote_count,
                from.video,
                from.vote_average,
                from.title,
                from.backdrop_path,
                from.overview,
                from.release_date
        )
    }

}