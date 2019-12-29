package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.model.Movie

interface GetMovies {

    operator fun invoke()  : ArrayList<Movie>

}