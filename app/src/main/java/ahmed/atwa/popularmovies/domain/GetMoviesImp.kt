package ahmed.atwa.popularmovies.domain

import ahmed.atwa.popularmovies.data.model.Movie
import ahmed.atwa.popularmovies.data.source.MovieRepository
import ahmed.atwa.popularmovies.ui.base.UIState
import javax.inject.Inject

class GetMoviesImp @Inject constructor(val repository: MovieRepository) : GetMovies {

    override fun invoke() : ArrayList<Movie> {
       return repository.getMovies()
    }

}