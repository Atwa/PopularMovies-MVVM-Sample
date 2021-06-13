package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.movies.data.Movie


sealed class MoviesViewState {
    class FetchingMoviesError(val errorMessage: String?) : MoviesViewState()
    class FetchingMoviesSuccess(val movies: List<Movie>) : MoviesViewState()
}
