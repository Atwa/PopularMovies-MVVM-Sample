package ahmed.atwa.popularmovies.data.entity

import ahmed.atwa.popularmovies.data.local.MovieLocal
import ahmed.atwa.popularmovies.data.remote.MovieRemote

class MovieMapper {


    fun mapFromLocal(from: MovieLocal): MovieEntity {
        return MovieEntity(
                from.id,
                if (from.isFav == 1) true else false,
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

    fun mapFromRemoteToLocal(from: MovieRemote, isFav: Int): MovieLocal {
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