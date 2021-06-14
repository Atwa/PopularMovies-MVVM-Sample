package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.movies.data.Movie
import androidx.paging.PagingData


sealed class MoviesViewState {
    class FetchingMoviesError(val errorMessage: String?) : MoviesViewState()
    class FetchingMoviesSuccess(val movies: PagingData<Movie>) : MoviesViewState()
}
