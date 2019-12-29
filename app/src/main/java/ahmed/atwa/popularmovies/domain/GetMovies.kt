package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.remote.model.Movie

interface GetMovies {

    suspend operator fun invoke()  : ArrayList<Movie>

}