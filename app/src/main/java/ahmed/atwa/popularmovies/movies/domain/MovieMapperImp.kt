package ahmed.atwa.popularmovies.movies.domain

import ahmed.atwa.popularmovies.movies.data.MovieLocal
import ahmed.atwa.popularmovies.movies.data.MovieRemote

class MovieMapperImp : MovieMapper {


    override fun mapFromRemoteToEntity(from: MovieRemote): MovieEntity {
        return MovieEntity(
                from.id,
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

    override fun mapFromEntityToLocal(from: MovieEntity): MovieLocal {
        return MovieLocal(
                from.id,
                from.posterPath,
                from.popularity,
                from.voteCount,
                from.video,
                from.voteAverage,
                from.title,
                from.backdropPath,
                from.overview,
                from.releaseDate
        )
    }


}