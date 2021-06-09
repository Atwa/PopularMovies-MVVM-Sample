package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.movies.domain.MovieEntity


sealed class MoviesViewState {
    object FetchingMoviesError : MoviesViewState()
    class FetchingMoviesSuccess(val movies: List<MovieEntity>?) : MoviesViewState()
}
