package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.movies.data.Movie

sealed class MovieModel {
    data class MovieItem(val movie: Movie) : MovieModel()
    data class SeparatorItem(val description: String) : MovieModel()
}
